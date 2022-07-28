package our.replacement.store.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import our.replacement.store.dto.FilterDto;
import our.replacement.store.dto.ImageDto;
import our.replacement.store.dto.ProductDto;
import our.replacement.store.dto.ShortProductDto;
import our.replacement.store.exception.EmptyImageFileException;
import our.replacement.store.exception.EntityNotFoundException;
import our.replacement.store.exception.InternalServerException;
import our.replacement.store.model.Image;
import our.replacement.store.model.Product;
import our.replacement.store.repository.ImageRepository;
import our.replacement.store.repository.ProductRepository;
import our.replacement.store.util.ImageMapper;
import our.replacement.store.util.ProductMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class ProductServiceImpl implements ProductService {

    private final Logger logger;

    private final ProductRepository productRepository;

    private final ImageRepository imageRepository;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ImageRepository imageRepository, JdbcTemplate jdbcTemplate,
                              ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.imageRepository = imageRepository;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        this.logger = LogManager.getLogger(ProductServiceImpl.class);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getProduct(Long id) {
        logger.info("Attempt to get product entity with id {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString(), Product.class.getSimpleName(),
                        "Product not found"));
        logger.info("Successful get product entity with id {}", id);
        return productMapper.convertToDto(product);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long id, ProductDto productDto, MultipartFile imageFile) {
        logger.info("Attempt to update product entity with id {}", id);
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                ImageDto saveImage = saveImage(imageFile);
                productDto.setImageUrl(saveImage.getUrl());
                productDto.setImageId(saveImage.getImageId());
            } catch (IOException e) {
                logger.fatal("IOException happened during image saving");
                throw new InternalServerException(this.getClass().getSimpleName());
            }
        } else {
            logger.info("User with id {} decided not to update image in product id {}",
                    productDto.getSellerId(), id);
        }

        Product productEntity = productMapper.convertToEntity(productDto);
        productRepository.save(productEntity);
        logger.info("Successful update entity with id {}", id);
        return productDto;
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        logger.info("Attempt to delete product entity with id {}", id);
        productRepository.deleteById(id);
        logger.info("Successful delete product entity with id {}", id);
    }

    @Override
    @Transactional
    public ProductDto createDeal(ProductDto productDto) {
        Long customerId = productDto.getCustomerId();
        Long productId = productDto.getProductId();
        logger.info("Attempt to create new deal with product id {}", productId);

        Product product = productRepository.findByIdAndActive(productId).orElseThrow(() -> new EntityNotFoundException(
                productId.toString(), Product.class.getSimpleName(), "Product not found"));

        ProductDto productDtoUpdated = productMapper.convertToDto(product);

        productDtoUpdated.setActive(false);
        productDtoUpdated.setCustomerId(customerId);
        productDtoUpdated.setDealTimestamp(LocalDateTime.now());

        Product editedProduct = productMapper.convertToEntity(productDtoUpdated);
        logger.info("Deal with product id {} created successfully", productId);
        return productMapper.convertToDto(productRepository.save(editedProduct));
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto productDto, MultipartFile multipartFile) {
        // TODO: change (sometime in future) operation order: save "image -> update product DTO -> save product" TO "save product -> save image -> update product"
        logger.info("Attempt to create new product");
        ImageDto imageDto;
        try {
            imageDto = saveImage(multipartFile);
        } catch (IOException e) {
            logger.fatal("IOException happened during image saving");
            throw new InternalServerException(this.getClass().getSimpleName());
        }

        productDto.setImageUrl(imageDto.getUrl());
        productDto.setImageId(imageDto.getImageId());

        productDto.setActive(true);
        productDto.setCreateTimestamp(LocalDateTime.now());

        Product productEntity = productMapper.convertToEntity(productDto);
        Product savedEntity = productRepository.save(productEntity);

        logger.info("Product with id {} created successfully", savedEntity.getProductId());
        return productMapper.convertToDto(savedEntity);
    }

    @Transactional
    public ImageDto saveImage(MultipartFile imageFile) throws IOException {
        if (imageFile == null || imageFile.isEmpty()) {
            throw new EmptyImageFileException();
        }

        UUID uuid = UUID.randomUUID();
        String extension = StringUtils.getFilenameExtension(imageFile.getOriginalFilename());
        String filename = uuid + "." + extension;
        // build/resources/main/static/minio <- if run from idea
        // ???? <- jar
        // TODO: implement (sometime in future) way to store files which is not dependent on how server is started
        Path pathToSave = Path.of("minio", filename);

        imageFile.transferTo(pathToSave);

        Image image = new Image();
        String url = File.separator + "minio" + File.separator + filename;
        image.setImageUrl(url);
        Image savedImage = imageRepository.save(image);

        return ImageMapper.convertToDto(savedImage);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShortProductDto> getActiveProduct(Long sellerId) {
        return productMapper.convertToShortDtoList(productRepository.findBySellerIdAndActive(sellerId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShortProductDto> getInactiveProduct(Long sellerId) {
        return productMapper.convertToShortDtoList(productRepository.findBySellerIdAndNotActive(sellerId));
    }

    @Override
    @Transactional(readOnly = true)
    // TODO: implement (sometime in future) more advanced search algorithm
    public List<ShortProductDto> search(List<FilterDto> filterDtoList) {
        List<FilterDto> notNullOrEmptyFilters = filterDtoList.stream()
                .filter(filterDto -> filterDto.getValue() != null && !filterDto.getValue().isEmpty())
                .toList();
        logger.info("Attempt to search with filters {}", notNullOrEmptyFilters);

        String query = buildQuery(notNullOrEmptyFilters);
        logger.debug("Search query: {}", query);

        MapSqlParameterSource queryMap = new MapSqlParameterSource();
        for (FilterDto filterDto : notNullOrEmptyFilters) {
            queryMap.addValue(filterDto.getName(), filterDto.getValue(), filterDto.getSqlType());
        }
        List<ShortProductDto> shortProductDtoList = namedParameterJdbcTemplate.query(query, queryMap,
                new BeanPropertyRowMapper<>(ShortProductDto.class));
        logger.info("Search result: {} products found", shortProductDtoList.size());
        return shortProductDtoList;
    }

    private String buildQuery(List<FilterDto> filterDtoList) {
        boolean needWhere = true;
        StringBuilder stringBuilder = new StringBuilder("""
                SELECT product_id,
                       seller_id,
                       tb_category.category_id,
                       tb_product.name,
                       tb_category.name as categoryName,
                       tb_product.image_id,
                       tb_image.image_url,
                       price,
                       deal_timestamp,
                       (SELECT CONCAT(first_name, ' ', last_name) as customerName
                        FROM avito_schema.tb_user
                        WHERE tb_user.user_id = tb_product.customer_id)
                FROM avito_schema.tb_product
                         JOIN avito_schema.tb_image ON tb_product.image_id = tb_image.image_id
                         JOIN avito_schema.tb_user ON tb_product.seller_id = tb_user.user_id
                         JOIN avito_schema.tb_category ON tb_product.category_id = tb_category.category_id""");
        for (int i = 0; i < filterDtoList.size(); i++) {
            FilterDto filter = filterDtoList.get(i);
            if (needWhere) {
                stringBuilder.append(" WHERE");
                needWhere = false;
            }
            stringBuilder.append(getFilterRepresentation(filter));
            if (i != filterDtoList.size() - 1) {
                stringBuilder.append("and");
            }
        }
        if (!needWhere) {
            stringBuilder.append(" AND ");
        } else {
            stringBuilder.append(" WHERE ");
        }
        stringBuilder.append(" tb_product.active is TRUE ORDER BY tb_product.create_timestamp DESC");
        return stringBuilder.toString();
    }

    private String getFilterRepresentation(FilterDto filter) {
        return switch (filter.getOperation()) {
            case EQUAL -> String.format(" tb_product.%s = :%s ", filter.getName(), filter.getName());
            case ILIKE -> String.format(" tb_product.%s ILIKE CONCAT('%%', :%s, '%%') ", filter.getName(), filter.getName());
        };
    }
}
