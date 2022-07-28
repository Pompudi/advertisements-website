package our.replacement.store.util;

import org.springframework.stereotype.Component;
import our.replacement.store.dto.CategoryDto;
import our.replacement.store.model.Category;

@Component
public class CategoryMapper {

    public Category convertToEntity(CategoryDto categoryDto) {
        Category category = new Category();

        category.setCategoryId(categoryDto.getCategoryId());
        category.setName(categoryDto.getName());

        return category;
    }

    public CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setName(category.getName());

        return categoryDto;
    }
}
