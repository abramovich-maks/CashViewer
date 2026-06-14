package com.cashviewer.domain.category.dto;

import lombok.Builder;

@Builder
public record DeleteCategoryResponse(
        Long id
) {
}
