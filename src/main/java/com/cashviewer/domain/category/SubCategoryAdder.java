package com.cashviewer.domain.category;

import com.cashviewer.domain.category.dto.AddSubCategoryRequestDto;
import com.cashviewer.domain.category.dto.AddSubCategoryResponseDto;
import com.cashviewer.domain.usercrud.UserEntity;
import com.cashviewer.domain.usercrud.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
class SubCategoryAdder {

    private final CategoryRetriever categoryRetriever;
    private final SubCategoryRetriever subCategoryRetriever;
    private final SubCategoryRepository subCategoryRepository;

    private final UserFacade userFacade;

    AddSubCategoryResponseDto addSubCategoryForUser(final Long currentUserId, final AddSubCategoryRequestDto requestDto) {
        CategoryEntity category = categoryRetriever.getCategoryEntity(requestDto.idCategory(), currentUserId);

        if (subCategoryRetriever.existsSubCategoryByUserIdAndName(currentUserId, category.getName(), requestDto.subCategoryName())) {
            throw new SubCategoryAlreadyExistsException(requestDto.subCategoryName());
        }

        UserEntity currentUser = userFacade.findById(currentUserId);

        SubCategoryEntity subCategory = new SubCategoryEntity();
        subCategory.setName(requestDto.subCategoryName());
        subCategory.setCategory(category);
        subCategory.setOwnerType(CategoryOwnerType.USER);
        subCategory.setUser(currentUser);

        subCategoryRepository.save(subCategory);
        return AddSubCategoryResponseDto.builder().subCategoryName(subCategory.getName()).build();
    }
}
