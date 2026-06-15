package com.cashviewer.domain.category;

import com.cashviewer.domain.category.dto.AddCategoryRequestDto;
import com.cashviewer.domain.category.dto.AddCategoryResponseDto;
import com.cashviewer.domain.category.dto.AddSubCategoryRequestDto;
import com.cashviewer.domain.category.dto.AddSubCategoryResponseDto;
import com.cashviewer.domain.category.dto.AllCategoryDto;
import com.cashviewer.domain.category.dto.CategoryDto;
import com.cashviewer.domain.category.dto.DeleteCategoryResponse;
import com.cashviewer.domain.category.dto.UpdateCategoryRequest;
import com.cashviewer.domain.category.dto.UpdateCategoryResponse;
import com.cashviewer.infrastructure.security.AuthenticationFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryFacade {

    private final CategoryRetriever categoryRetriever;
    private final AuthenticationFacade authenticationFacade;
    private final CategoryAdder categoryAdder;
    private final SubCategoryAdder subCategoryAdder;
    private final CategoryEditor categoryEditor;
    private final CategoryDeleter categoryDeleter;


    public CategoryDto getCategoryById(Long categoryId) {
        Long currentUserId = authenticationFacade.getCurrentUserId();
        return categoryRetriever.getCategory(categoryId, currentUserId);
    }

    public AllCategoryDto getAllCategories() {
        Long currentUserId = authenticationFacade.getCurrentUserId();
        return categoryRetriever.getAllCategories(currentUserId);
    }

    public AddCategoryResponseDto addCategory(AddCategoryRequestDto requestDto) {
        Long currentUserId = authenticationFacade.getCurrentUserId();
        return categoryAdder.addCategoryForUser(currentUserId, requestDto);
    }

    public UpdateCategoryResponse updateCategory(UpdateCategoryRequest requestDto) {
        Long currentUserId = authenticationFacade.getCurrentUserId();
        return categoryEditor.updateCategory(currentUserId, requestDto);
    }

    public DeleteCategoryResponse deleteCategory(Long categoryId) {
        Long currentUserId = authenticationFacade.getCurrentUserId();
        return categoryDeleter.deleteCategory(categoryId, currentUserId);
    }

    public AddSubCategoryResponseDto addSubCategory(AddSubCategoryRequestDto requestDto) {
        Long currentUserId = authenticationFacade.getCurrentUserId();
        return subCategoryAdder.addSubCategoryForUser(currentUserId, requestDto);
    }
    /*
     todo :
      editSubCategory
      deleteSubCategory
      getOnlyCategories
      getIncomeCategory
      getExpenseCategory
      getDebtCategory
     */
}