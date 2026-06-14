package com.cashviewer.domain.category;

import com.cashviewer.domain.category.dto.UpdateCategoryRequest;
import com.cashviewer.domain.category.dto.UpdateCategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class CategoryEditor {

    private final CategoryRetriever categoryRetriever;
    private final CategoryRepository categoryRepository;

    UpdateCategoryResponse updateCategory(final Long currentUserId, final UpdateCategoryRequest requestDto) {
        CategoryEntity categoryEntity = categoryRetriever.getCategoryEntity(requestDto.idCategory(), currentUserId);
        if (categoryEntity.getOwnerType().equals(CategoryOwnerType.SYSTEM)) {
            throw new CannotEditSystemCategoryException();
        }
        if (!categoryEntity.getName().equals(requestDto.newCategoryName()) && categoryRetriever.existsCategoryByUserIdAndName(currentUserId, requestDto.newCategoryName())) {
            throw new CategoryAlreadyExistsException(requestDto.newCategoryName());
        }

        categoryEntity.setName(requestDto.newCategoryName());
        categoryRepository.save(categoryEntity);

        return UpdateCategoryResponse.builder().idCategory(categoryEntity.getId()).newCategoryName(categoryEntity.getName()).build();
    }
}
