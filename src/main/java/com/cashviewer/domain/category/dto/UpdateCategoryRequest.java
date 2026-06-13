package com.cashviewer.domain.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import static com.cashviewer.infrastructure.api.validation.ValidationConstants.CATEGORY_MAX_SIZE;
import static com.cashviewer.infrastructure.api.validation.ValidationConstants.CATEGORY_MIN_SIZE;

public record UpdateCategoryRequest(
        @NotNull
        Long idCategory,

        @NotBlank
        @Size(min = CATEGORY_MIN_SIZE, max = CATEGORY_MAX_SIZE, message = "{category.name.size}")
        String newCategoryName

) {
}
