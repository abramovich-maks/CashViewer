package com.cashviewer.domain.category.dto;


import com.cashviewer.domain.category.CategoryOwnerType;

public record SubCategoryDto(
        Long id,
        String name,
        CategoryOwnerType ownerType
) {
}