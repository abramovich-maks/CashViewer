package com.cashviewer.domain.category;

import com.cashviewer.domain.category.dto.AllCategoryDto;
import com.cashviewer.domain.category.dto.CategoryDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryFacadeTest {

    CategoryRepository categoryRepository = new CategoryRepositoryTestImpl();
    CategoryEntityMapper categoryEntityMapper = new CategoryEntityMapperImpl();
    CategoryRetriever categoryRetriever = new CategoryRetriever(categoryRepository, categoryEntityMapper);
    CategoryFacade categoryFacade = new CategoryFacade(categoryRetriever);

    @Test
    public void should_exception_when_find_category_with_not_found_id() {
        // given
        long notFoundIdCategory = 1L;
        // when && then
        CategoryNotFoundException exception = assertThrows(CategoryNotFoundException.class, () -> categoryFacade.getCategoryById(notFoundIdCategory));
        assertThat(exception.getMessage()).isEqualTo("Category with id: " + notFoundIdCategory + " not found");
    }

    @Test
    void should_get_category_by_id() {
        // given

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Food");
        categoryEntity.setType(CategoryType.EXPENSE);

        categoryRepository.save(categoryEntity);
        // when
        CategoryDto category = categoryFacade.getCategoryById(1L);
        // then
        assertThat(category.id()).isEqualTo(1L);
        assertThat(category.name()).isEqualTo("Food");
    }

    @Test
    void should_get_all_categories() {
        // given
        CategoryEntity firstCategory = new CategoryEntity();
        firstCategory.setId(1L);
        firstCategory.setName("Home");
        firstCategory.setType(CategoryType.EXPENSE);

        CategoryEntity secondCategory = new CategoryEntity();
        secondCategory.setId(2L);
        secondCategory.setName("Food");
        secondCategory.setType(CategoryType.EXPENSE);

        categoryRepository.save(firstCategory);
        categoryRepository.save(secondCategory);
        // when
        AllCategoryDto allCategories = categoryFacade.getAllCategories();
        // then
        assertThat(allCategories.categoryDtos()).hasSize(2);
        assertThat(allCategories.categoryDtos())
                .extracting(CategoryDto::id, CategoryDto::name)
                .containsExactlyInAnyOrder(
                        tuple(1L, "Home"),
                        tuple(2L, "Food")
                );
    }
}
