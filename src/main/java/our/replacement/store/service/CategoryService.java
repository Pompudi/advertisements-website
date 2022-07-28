package our.replacement.store.service;

import our.replacement.store.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto getCategory(Long id);

    List<CategoryDto> getAllCategories();
}
