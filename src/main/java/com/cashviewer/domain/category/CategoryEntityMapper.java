package com.cashviewer.domain.category;

import com.cashviewer.domain.category.dto.AllCategoryDto;
import com.cashviewer.domain.category.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
interface CategoryEntityMapper {
    CategoryEntity toEntity(CategoryDto categoryDto);

    CategoryDto toCategoryDto(CategoryEntity categoryEntity);

    List<CategoryDto> toCategoryDtoList(List<CategoryEntity> categoryEntities);

    default AllCategoryDto toCategoryListDto(List<CategoryEntity> categoryEntities) {
        return new AllCategoryDto(toCategoryDtoList(categoryEntities));
    }
}