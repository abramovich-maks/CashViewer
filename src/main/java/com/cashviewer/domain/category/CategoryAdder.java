package com.cashviewer.domain.category;

import com.cashviewer.domain.category.dto.AddCategoryRequestDto;
import com.cashviewer.domain.category.dto.AddCategoryResponseDto;
import com.cashviewer.domain.usercrud.UserEntity;
import com.cashviewer.domain.usercrud.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class CategoryAdder {

    private final CategoryRetriever categoryRetriever;
    private final CategoryRepository categoryRepository;

    private final UserFacade userFacade;

    AddCategoryResponseDto addCategoryForUser(final Long currentUserId, AddCategoryRequestDto requestDto) {
        if (categoryRetriever.existsCategoryByUserIdAndName(currentUserId, requestDto.categoryName())) {
            throw new CategoryAlreadyExistsException(requestDto.categoryName());
        }

        UserEntity currentUser = userFacade.findById(currentUserId);

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setType(requestDto.type());
        categoryEntity.setName(requestDto.categoryName());
        categoryEntity.setOwnerType(CategoryOwnerType.USER);
        categoryEntity.setUser(currentUser);

        CategoryEntity save = categoryRepository.save(categoryEntity);
        return AddCategoryResponseDto.builder().categoryName(save.getName()).build();
    }
}
