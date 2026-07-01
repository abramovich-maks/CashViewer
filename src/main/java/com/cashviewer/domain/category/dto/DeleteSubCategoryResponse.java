package com.cashviewer.domain.category.dto;

import lombok.Builder;

@Builder
public record DeleteSubCategoryResponse(
        Long categoryId,
        Long subCategoryId
) {
}
