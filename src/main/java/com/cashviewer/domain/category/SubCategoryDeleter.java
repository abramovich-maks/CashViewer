package com.cashviewer.domain.category;

import com.cashviewer.domain.category.dto.DeleteSubCategoryRequest;
import com.cashviewer.domain.category.dto.DeleteSubCategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class SubCategoryDeleter {

    private final SubCategoryRetriever subCategoryRetriever;
    private final SubCategoryRepository subCategoryRepository;

    DeleteSubCategoryResponse deleteSubCategory(final Long currentUserId, final DeleteSubCategoryRequest request) {
        SubCategoryEntity subCategoryEntity = subCategoryRetriever.getSubCategoryEntity(request.idSubCategory(), request.idCategory(), currentUserId);

        if (subCategoryEntity.getOwnerType().equals(CategoryOwnerType.SYSTEM)) {
            throw new CannotDeleteSystemSubCategoryException();
        }

        subCategoryRepository.delete(subCategoryEntity);
        return DeleteSubCategoryResponse.builder().categoryId(subCategoryEntity.getCategory().getId()).subCategoryId(subCategoryEntity.getId()).build();
    }
}
