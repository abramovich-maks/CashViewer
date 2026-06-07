package com.cashviewer.domain.category;

import com.cashviewer.domain.category.dto.AllCategoryDto;
import com.cashviewer.domain.category.dto.CategoryDto;
import com.cashviewer.infrastructure.security.AuthenticationFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryFacade {

    private final CategoryRetriever categoryRetriever;
    private final AuthenticationFacade authenticationFacade;


    public CategoryDto getCategoryById(Long categoryId) {
        return categoryRetriever.getCategory(categoryId);
    }

    public AllCategoryDto getAllCategories() {
        Long currentUserId = authenticationFacade.getCurrentUserId();
        return categoryRetriever.getAllCategories(currentUserId);
    }

    /*
     todo :
      addCategory
      editCategory
      deleteCategory
      addSubCategory
      editSubCategory
      deleteSubCategory
     */
}