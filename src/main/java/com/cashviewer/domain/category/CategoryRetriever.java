package com.cashviewer.domain.category;

import com.cashviewer.domain.category.dto.AllCategoryDto;
import com.cashviewer.domain.category.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
class CategoryRetriever {

    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    public CategoryDto getCategory(Long categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new CategoryNotFoundException(categoryId));
        return categoryEntityMapper.toCategoryDto(category);
    }

    public AllCategoryDto getAllCategories() {
        List<CategoryEntity> all = categoryRepository.findAll();
        return categoryEntityMapper.toCategoryListDto(all);
    }
}
