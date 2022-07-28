package our.replacement.store.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import our.replacement.store.dto.ProductDto;
import our.replacement.store.dto.ShortProductDto;
import our.replacement.store.model.Product;
import our.replacement.store.repository.CategoryRepository;
import our.replacement.store.repository.ImageRepository;
import our.replacement.store.repository.UserRepository;

import java.util.List;

@Component
public class ProductMapper {

    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductMapper(ImageRepository imageRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setProductId(product.getProductId());

        productDto.setSellerId(product.getSeller().getUserId());
        productDto.setCategoryId(product.getCategory().getCategoryId());
        productDto.setCategoryName(product.getCategory().getName());
        productDto.setImageId(product.getImage().getImageId());
        productDto.setImageUrl(product.getImage().getImageUrl());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setAddress(product.getAddress());
        productDto.setActive(product.getActive());
        productDto.setDescription(product.getDescription());
        productDto.setCreateTimestamp(product.getCreateTimestamp());
        productDto.setDealTimestamp(product.getDealTimestamp());

        if (product.getCustomer() != null) {
            productDto.setCustomerId(product.getCustomer().getUserId());
            productDto.setCustomerName(product.getCustomer().getFirstName() + " "
                    + product.getCustomer().getLastName());
        }

        return productDto;
    }

    public ShortProductDto convertToShortDto(Product product) {
        ShortProductDto shortProductDto = new ShortProductDto();

        shortProductDto.setProductId(product.getProductId());
        shortProductDto.setSellerId(product.getSeller().getUserId());
        shortProductDto.setCategoryId(product.getCategory().getCategoryId());
        shortProductDto.setCategoryName(product.getCategory().getName());
        shortProductDto.setImageId(product.getImage().getImageId());
        shortProductDto.setImageUrl(product.getImage().getImageUrl());
        shortProductDto.setName(product.getName());
        shortProductDto.setPrice(product.getPrice());
        shortProductDto.setDealTimestamp(product.getDealTimestamp());

        if (product.getCustomer() != null) {
            shortProductDto.setCustomerName(product.getCustomer().getFirstName() + " "
                    + product.getCustomer().getLastName());
        }

        return shortProductDto;
    }

    public List<ShortProductDto> convertToShortDtoList(List<Product> productList) {
        return productList.stream()
                .map(this::convertToShortDto)
                .toList();
    }

    public Product convertToEntity(ProductDto productDto) {
        Product product = new Product();

        product.setProductId(productDto.getProductId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setAddress(productDto.getAddress());
        product.setActive(productDto.getActive());
        product.setCreateTimestamp(productDto.getCreateTimestamp());
        product.setDealTimestamp(productDto.getDealTimestamp());
        product.setDescription(productDto.getDescription());

        product.setImage(imageRepository.getReferenceById(productDto.getImageId()));
        product.setCategory(categoryRepository.getReferenceById(productDto.getCategoryId()));
        product.setSeller(userRepository.getReferenceById(productDto.getSellerId()));

        if (productDto.getCustomerId() != null) {
            product.setCustomer(userRepository.getReferenceById(productDto.getCustomerId()));
        }

        return product;
    }
}
