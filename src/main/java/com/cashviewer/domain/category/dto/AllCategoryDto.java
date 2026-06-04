package com.cashviewer.domain.category.dto;

import java.util.List;

public record AllCategoryDto(
        List<CategoryDto> categoryDtos
) {
}