package com.cashviewer.domain.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
class SubCategoryRetriever {

    private final SubCategoryRepository subCategoryRepository;

    boolean existsSubCategoryByUserIdAndName(final Long currentUserId, final Long categoryId, final String subCategoryName) {
        return subCategoryRepository.existsSubCategoryByNameAndCategoryId(subCategoryName, categoryId, currentUserId);
    }

    public SubCategoryEntity getSubCategoryEntity(Long subCategoryId, Long categoryId, Long userId) {
        return subCategoryRepository.findAvailableSubCategoryByIdAndCategoryIdAndUserId(subCategoryId, categoryId, userId)
                .orElseThrow(() -> new SubCategoryNotFoundException(subCategoryId));
    }

    boolean existsSubCategoryByUserIdAndCategoryId(final String subCategoryName, final Long categoryId, final Long currentUserId) {
        return subCategoryRepository.existsSubCategoryByNameAndCategoryId(subCategoryName, categoryId, currentUserId);
    }
}
