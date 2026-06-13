package com.cashviewer.domain.category.dto;

import lombok.Builder;

@Builder
public record UpdateCategoryResponse(
        Long idCategory,
        String newCategoryName
) {
}
