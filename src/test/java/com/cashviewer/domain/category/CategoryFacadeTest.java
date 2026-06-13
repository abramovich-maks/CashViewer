package com.cashviewer.domain.category;

import com.cashviewer.domain.category.dto.AddCategoryRequestDto;
import com.cashviewer.domain.category.dto.AddCategoryResponseDto;
import com.cashviewer.domain.category.dto.AllCategoryDto;
import com.cashviewer.domain.category.dto.CategoryDto;
import com.cashviewer.domain.usercrud.UserEntity;
import com.cashviewer.domain.usercrud.UserFacade;
import com.cashviewer.infrastructure.security.AuthenticationFacade;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CategoryFacadeTest {

    CategoryRepository categoryRepository = new CategoryRepositoryTestImpl();
    CategoryEntityMapper categoryEntityMapper = new CategoryEntityMapperImpl();
    AuthenticationFacade authenticationFacade = mock(AuthenticationFacade.class);
    UserFacade userFacade = mock(UserFacade.class);
    CategoryRetriever categoryRetriever = new CategoryRetriever(categoryRepository, categoryEntityMapper);
    CategoryAdder categoryAdder = new CategoryAdder(categoryRetriever, categoryRepository, userFacade);
    CategoryFacade categoryFacade = new CategoryFacade(categoryRetriever, authenticationFacade, categoryAdder);

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
        firstCategory.setOwnerType(CategoryOwnerType.SYSTEM);
        firstCategory.setType(CategoryType.EXPENSE);

        CategoryEntity secondCategory = new CategoryEntity();
        secondCategory.setId(2L);
        secondCategory.setName("Food");
        secondCategory.setOwnerType(CategoryOwnerType.SYSTEM);
        secondCategory.setType(CategoryType.EXPENSE);

        when(authenticationFacade.getCurrentUserId()).thenReturn(1L);

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

    @Test
    void should_add_category() {
        // given
        AddCategoryRequestDto requestDto = new AddCategoryRequestDto(CategoryType.INCOME, "Present");
        UserEntity user = new UserEntity();
        user.setId(1L);
        when(userFacade.findById(1L)).thenReturn(user);
        when(authenticationFacade.getCurrentUserId()).thenReturn(1L);
        // when
        AddCategoryResponseDto responseDto = categoryFacade.addCategory(requestDto);
        // then
        AllCategoryDto allCategories = categoryFacade.getAllCategories();
        assertThat(allCategories.categoryDtos()).hasSize(1);
        assertThat(responseDto.categoryName()).isEqualTo("Present");
        assertThat(allCategories.categoryDtos())
                .extracting(CategoryDto::name)
                .containsExactlyInAnyOrder("Present");
    }

    @Test
    void should_exception_when_user_add_exist_category() {
        // given
        String categoryName = "Present";
        AddCategoryRequestDto requestDto = new AddCategoryRequestDto(CategoryType.INCOME, categoryName);
        UserEntity user = new UserEntity();
        user.setId(1L);
        when(userFacade.findById(1L)).thenReturn(user);
        when(authenticationFacade.getCurrentUserId()).thenReturn(1L);
        categoryFacade.addCategory(requestDto);
        // when && then
        CategoryAlreadyExistsException exception = assertThrows(CategoryAlreadyExistsException.class, () -> categoryFacade.addCategory(requestDto));
        assertThat(exception.getMessage()).isEqualTo("Category with name " + categoryName + " already exists");
        AllCategoryDto allCategories = categoryFacade.getAllCategories();
        assertThat(allCategories.categoryDtos()).hasSize(1);

    }
}
