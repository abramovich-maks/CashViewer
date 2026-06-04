package com.cashviewer.domain.category;

import com.cashviewer.domain.category.dto.AllCategoryDto;
import com.cashviewer.domain.category.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryFacade {

    private final CategoryRetriever categoryRetriever;


    public CategoryDto getCategoryById(Long categoryId) {
        return categoryRetriever.getCategory(categoryId);
    }

    public AllCategoryDto getAllCategories() {
        return categoryRetriever.getAllCategories();
    }
}