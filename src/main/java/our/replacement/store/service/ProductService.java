package our.replacement.store.service;

import org.springframework.web.multipart.MultipartFile;
import our.replacement.store.dto.FilterDto;
import our.replacement.store.dto.ImageDto;
import our.replacement.store.dto.ProductDto;
import our.replacement.store.dto.ShortProductDto;
import our.replacement.store.model.Category;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    ProductDto getProduct(Long id);

    ProductDto updateProduct(Long id, ProductDto productDto, MultipartFile imageFile);

    void deleteProduct(Long id);

    ProductDto createDeal(ProductDto productDto);

    ProductDto createProduct(ProductDto productDto, MultipartFile imageFile);

    List<ShortProductDto> search(List<FilterDto> filterDtoList);

    ImageDto saveImage(MultipartFile imageFile) throws IOException;

    List<ShortProductDto> getActiveProduct(Long sellerId);

    List<ShortProductDto> getInactiveProduct(Long sellerId);
}
