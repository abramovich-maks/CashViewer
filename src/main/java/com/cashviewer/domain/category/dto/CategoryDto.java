package com.cashviewer.domain.category.dto;

import com.cashviewer.domain.category.CategoryType;

import java.util.List;


public record CategoryDto(
        Long id,
        String name,
        CategoryType type,
        List<SubCategoryDto> subCategories
) {
}