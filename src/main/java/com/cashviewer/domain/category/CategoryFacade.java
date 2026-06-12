package com.cashviewer.domain.category;

import com.cashviewer.domain.category.dto.AddCategoryRequestDto;
import com.cashviewer.domain.category.dto.AddCategoryResponseDto;
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
    private final CategoryAdder categoryAdder;


    public CategoryDto getCategoryById(Long categoryId) {
        return categoryRetriever.getCategory(categoryId);
    }

    public AllCategoryDto getAllCategories() {
        Long currentUserId = authenticationFacade.getCurrentUserId();
        return categoryRetriever.getAllCategories(currentUserId);
    }

    public AddCategoryResponseDto addCategory(AddCategoryRequestDto requestDto) {
        Long currentUserId = authenticationFacade.getCurrentUserId();
        return categoryAdder.addCategoryForUser(currentUserId, requestDto);
    }

    /*
     todo :
      editCategory
      deleteCategory
      addSubCategory
      editSubCategory
      deleteSubCategory
     */
}