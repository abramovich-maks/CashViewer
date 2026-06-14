package com.cashviewer.domain.category;

import com.cashviewer.domain.category.dto.DeleteCategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class CategoryDeleter {

    private final CategoryRetriever categoryRetriever;
    private final CategoryRepository categoryRepository;

    DeleteCategoryResponse deleteCategory(Long categoryId, Long currentUserId) {

        CategoryEntity category = categoryRetriever.getCategoryEntity(categoryId, currentUserId);

        if (category.getOwnerType() == CategoryOwnerType.SYSTEM) {
            throw new CannotDeleteSystemCategoryException();
        }

        categoryRepository.delete(category);
        return DeleteCategoryResponse.builder().id(category.getId()).build();
    }
}
