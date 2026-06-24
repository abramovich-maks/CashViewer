package com.cashviewer.domain.category.dto;

import lombok.Builder;

@Builder
public record UpdateSubCategoryResponse(
        String subCategoryName
) {
}
