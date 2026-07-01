package com.cashviewer.domain.category.dto;

import jakarta.validation.constraints.NotNull;

public record DeleteSubCategoryRequest(
        @NotNull(message = "{id.category.not.null}")
        Long idCategory,

        @NotNull(message = "{id.subcategory.not.null}")
        Long idSubCategory
) {
}
