package our.replacement.store.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import our.replacement.store.dto.CategoryDto;
import our.replacement.store.exception.EntityNotFoundException;
import our.replacement.store.model.Category;
import our.replacement.store.repository.CategoryRepository;
import our.replacement.store.util.CategoryMapper;

import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final Logger logger;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.logger = LogManager.getLogger(CategoryServiceImpl.class);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto getCategory(Long id) {
        logger.info("Attempt to get category with id {}", id);
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(id.toString(), Category.class.getSimpleName(), "Category not found"));
        logger.info("Successful find category with id {}", id);
        return categoryMapper.convertToDto(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::convertToDto)
                .toList();
    }
}
