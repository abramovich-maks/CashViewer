package com.cashviewer.domain.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import static com.cashviewer.infrastructure.api.validation.ValidationConstants.SUBCATEGORY_MAX_SIZE;
import static com.cashviewer.infrastructure.api.validation.ValidationConstants.SUBCATEGORY_MIN_SIZE;

public record UpdateSubCategoryRequest(
        @NotNull(message = "{id.category.not.null}")
        Long idCategory,

        @NotNull(message = "{id.subcategory.not.null}")
        Long idSubCategory,

        @NotBlank
        @Size(min = SUBCATEGORY_MIN_SIZE, max = SUBCATEGORY_MAX_SIZE, message = "{subcategory.name.size}")
        String newSubCategoryName
) {
}
