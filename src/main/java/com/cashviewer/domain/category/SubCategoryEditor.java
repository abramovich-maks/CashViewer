package com.cashviewer.domain.category;

import com.cashviewer.domain.category.dto.UpdateSubCategoryRequest;
import com.cashviewer.domain.category.dto.UpdateSubCategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
class SubCategoryEditor {

    private final SubCategoryRetriever subCategoryRetriever;
    private final SubCategoryRepository subCategoryRepository;

    UpdateSubCategoryResponse updateSubCategory(final Long currentUserId, final UpdateSubCategoryRequest requestDto) {

        SubCategoryEntity subCategoryEntity = subCategoryRetriever.getSubCategoryEntity(requestDto.idSubCategory(), requestDto.idCategory(), currentUserId);

        if (subCategoryEntity.getOwnerType().equals(CategoryOwnerType.SYSTEM)) {
            throw new CannotEditSystemSubCategoryException();
        }
        if (!subCategoryEntity.getName().equals(requestDto.newSubCategoryName()) && subCategoryRetriever.existsSubCategoryByUserIdAndCategoryId(requestDto.newSubCategoryName(), requestDto.idCategory(), currentUserId)) {
            throw new SubCategoryAlreadyExistsException(requestDto.newSubCategoryName());
        }

        subCategoryEntity.setName(requestDto.newSubCategoryName());
        subCategoryRepository.save(subCategoryEntity);

        return UpdateSubCategoryResponse.builder().subCategoryName(subCategoryEntity.getName()).build();
    }

}
