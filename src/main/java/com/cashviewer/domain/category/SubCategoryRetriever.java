package com.cashviewer.domain.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
class SubCategoryRetriever {

    private final SubCategoryRepository subCategoryRepository;

    boolean existsSubCategoryByUserIdAndName(final Long currentUserId, final String categoryName, final String subCategoryName) {
        return subCategoryRepository.existsAvailableSubCategoryByName(currentUserId, categoryName, subCategoryName);
    }
}
