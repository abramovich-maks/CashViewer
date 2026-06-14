package com.cashviewer.domain.category;

import com.cashviewer.domain.category.dto.AddCategoryRequestDto;
import com.cashviewer.domain.category.dto.AddCategoryResponseDto;
import com.cashviewer.domain.category.dto.AllCategoryDto;
import com.cashviewer.domain.category.dto.CategoryDto;
import com.cashviewer.domain.category.dto.DeleteCategoryResponse;
import com.cashviewer.domain.category.dto.UpdateCategoryRequest;
import com.cashviewer.domain.category.dto.UpdateCategoryResponse;
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
    CategoryEditor categoryEditor = new CategoryEditor(categoryRetriever, categoryRepository);
    CategoryDeleter categoryDeleter = new CategoryDeleter(categoryRetriever, categoryRepository);
    CategoryFacade categoryFacade = new CategoryFacade(categoryRetriever, authenticationFacade, categoryAdder, categoryEditor, categoryDeleter);

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
        categoryEntity.setOwnerType(CategoryOwnerType.USER);

        UserEntity user = new UserEntity();
        user.setId(1L);

        categoryEntity.setUser(user);
        when(authenticationFacade.getCurrentUserId()).thenReturn(1L);

        categoryRepository.save(categoryEntity);
        // when
        CategoryDto category = categoryFacade.getCategoryById(1L);
        // then
        assertThat(category.id()).isEqualTo(1L);
        assertThat(category.name()).isEqualTo("Food");
    }

    @Test
    void should_not_get_category_of_another_user() {
        // given
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Food");
        categoryEntity.setType(CategoryType.EXPENSE);
        categoryEntity.setOwnerType(CategoryOwnerType.USER);

        UserEntity user = new UserEntity();
        user.setId(1L);
        categoryEntity.setUser(user);
        categoryRepository.save(categoryEntity);

        when(authenticationFacade.getCurrentUserId()).thenReturn(1L);
        // then
        CategoryDto categoryForFirstUser = categoryFacade.getCategoryById(1L);
        assertThat(categoryForFirstUser.id()).isEqualTo(1L);
        assertThat(categoryForFirstUser.name()).isEqualTo("Food");
        // when && then
        when(authenticationFacade.getCurrentUserId()).thenReturn(2L);
        assertThrows(CategoryNotFoundException.class, () -> categoryFacade.getCategoryById(1L));
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

    @Test
    void should_update_category_name() {
        // given
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Food");
        categoryEntity.setType(CategoryType.EXPENSE);
        categoryEntity.setOwnerType(CategoryOwnerType.USER);

        UserEntity user = new UserEntity();
        user.setId(1L);

        categoryEntity.setUser(user);
        when(authenticationFacade.getCurrentUserId()).thenReturn(1L);

        categoryRepository.save(categoryEntity);
        CategoryDto category = categoryFacade.getCategoryById(1L);
        assertThat(category.name()).isEqualTo("Food");

        UpdateCategoryRequest update = new UpdateCategoryRequest(1L, "Update Food");
        // when
        UpdateCategoryResponse updateCategory = categoryFacade.updateCategory(update);
        // then
        assertThat(updateCategory.newCategoryName()).isEqualTo("Update Food");
        CategoryDto updatedCategory = categoryFacade.getCategoryById(1L);
        assertThat(updatedCategory.name()).isEqualTo("Update Food");
    }

    @Test
    void should_not_update_system_category() {
        // given
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Food");
        categoryEntity.setType(CategoryType.EXPENSE);
        categoryEntity.setOwnerType(CategoryOwnerType.SYSTEM);

        UserEntity user = new UserEntity();
        user.setId(1L);

        categoryEntity.setUser(user);
        when(authenticationFacade.getCurrentUserId()).thenReturn(1L);

        categoryRepository.save(categoryEntity);

        UpdateCategoryRequest update = new UpdateCategoryRequest(1L, "Update Food");
        // when && then
        CannotEditSystemCategoryException exception = assertThrows(CannotEditSystemCategoryException.class, () -> categoryFacade.updateCategory(update));
        assertThat(exception.getMessage()).isEqualTo("System category cannot be edited");
        CategoryDto updatedCategory = categoryFacade.getCategoryById(1L);
        assertThat(updatedCategory.name()).isEqualTo("Food");
    }

    @Test
    void should_not_update_category_of_another_user() {
        // given


        CategoryEntity categoryEntity = new CategoryEntity();
        long idCategory = 1L;
        categoryEntity.setId(idCategory);
        categoryEntity.setName("Food");
        categoryEntity.setType(CategoryType.EXPENSE);
        categoryEntity.setOwnerType(CategoryOwnerType.USER);

        UserEntity user = new UserEntity();
        user.setId(1L);

        categoryEntity.setUser(user);
        when(authenticationFacade.getCurrentUserId()).thenReturn(1L);

        categoryRepository.save(categoryEntity);
        CategoryDto category = categoryFacade.getCategoryById(idCategory);
        assertThat(category.name()).isEqualTo("Food");

        when(authenticationFacade.getCurrentUserId()).thenReturn(2L);
        UpdateCategoryRequest update = new UpdateCategoryRequest(idCategory, "Update Food");
        // when
        CategoryNotFoundException exception = assertThrows(CategoryNotFoundException.class, () -> categoryFacade.updateCategory(update));
        // then
        assertThat(exception.getMessage()).isEqualTo("Category with id: " + idCategory + " not found");

        when(authenticationFacade.getCurrentUserId()).thenReturn(1L);
        CategoryDto categoryByFirstUser = categoryFacade.getCategoryById(idCategory);
        assertThat(categoryByFirstUser.name()).isEqualTo("Food");
    }

    @Test
    void should_throw_exception_when_new_name_already_exists() {
        // given
        CategoryEntity firstCategory = new CategoryEntity();
        firstCategory.setId(1L);
        firstCategory.setName("Food");
        firstCategory.setType(CategoryType.EXPENSE);
        firstCategory.setOwnerType(CategoryOwnerType.USER);

        CategoryEntity secondCategory = new CategoryEntity();
        secondCategory.setId(2L);
        secondCategory.setName("Home");
        secondCategory.setType(CategoryType.EXPENSE);
        secondCategory.setOwnerType(CategoryOwnerType.USER);


        UserEntity user = new UserEntity();
        user.setId(1L);

        firstCategory.setUser(user);
        secondCategory.setUser(user);
        when(authenticationFacade.getCurrentUserId()).thenReturn(1L);

        categoryRepository.save(firstCategory);
        categoryRepository.save(secondCategory);
        AllCategoryDto allCategories = categoryFacade.getAllCategories();
        assertThat(allCategories.categoryDtos()).hasSize(2);
        assertThat(allCategories.categoryDtos())
                .extracting(CategoryDto::id, CategoryDto::name)
                .containsExactlyInAnyOrder(
                        tuple(1L, "Food"),
                        tuple(2L, "Home")
                );

        UpdateCategoryRequest update = new UpdateCategoryRequest(2L, "Food");
        // when
        CategoryAlreadyExistsException exception = assertThrows(CategoryAlreadyExistsException.class, () -> categoryFacade.updateCategory(update));
        // then
        assertThat(exception.getMessage()).isEqualTo("Category with name Food already exists");
    }

    @Test
    void should_not_throw_exception_when_name_is_the_same() {
        // given
        CategoryEntity firstCategory = new CategoryEntity();
        firstCategory.setId(1L);
        firstCategory.setName("Food");
        firstCategory.setType(CategoryType.EXPENSE);
        firstCategory.setOwnerType(CategoryOwnerType.USER);

        CategoryEntity secondCategory = new CategoryEntity();
        secondCategory.setId(2L);
        secondCategory.setName("Home");
        secondCategory.setType(CategoryType.EXPENSE);
        secondCategory.setOwnerType(CategoryOwnerType.USER);


        UserEntity user = new UserEntity();
        user.setId(1L);

        firstCategory.setUser(user);
        secondCategory.setUser(user);
        when(authenticationFacade.getCurrentUserId()).thenReturn(1L);

        categoryRepository.save(firstCategory);
        categoryRepository.save(secondCategory);
        AllCategoryDto allCategories = categoryFacade.getAllCategories();
        assertThat(allCategories.categoryDtos()).hasSize(2);
        assertThat(allCategories.categoryDtos())
                .extracting(CategoryDto::id, CategoryDto::name)
                .containsExactlyInAnyOrder(
                        tuple(1L, "Food"),
                        tuple(2L, "Home")
                );

        UpdateCategoryRequest update = new UpdateCategoryRequest(2L, "Home");
        // when
        categoryFacade.updateCategory(update);
        // then
        assertThat(allCategories.categoryDtos())
                .extracting(CategoryDto::id, CategoryDto::name)
                .containsExactlyInAnyOrder(
                        tuple(1L, "Food"),
                        tuple(2L, "Home")
                );
    }

    @Test
    void should_not_update_category_when_system_category_with_same_name_exists() {
        // given
        CategoryEntity systemCategory = new CategoryEntity();
        systemCategory.setId(1L);
        systemCategory.setName("Food");
        systemCategory.setType(CategoryType.EXPENSE);
        systemCategory.setOwnerType(CategoryOwnerType.SYSTEM);

        CategoryEntity userCategory = new CategoryEntity();
        userCategory.setId(2L);
        userCategory.setName("Home");
        userCategory.setType(CategoryType.EXPENSE);
        userCategory.setOwnerType(CategoryOwnerType.USER);

        UserEntity user = new UserEntity();
        user.setId(1L);

        userCategory.setUser(user);
        when(authenticationFacade.getCurrentUserId()).thenReturn(1L);

        categoryRepository.save(systemCategory);
        categoryRepository.save(userCategory);

        UpdateCategoryRequest update = new UpdateCategoryRequest(2L, "Food");

        // when && then
        CategoryAlreadyExistsException exception = assertThrows(CategoryAlreadyExistsException.class, () -> categoryFacade.updateCategory(update));

        assertThat(exception.getMessage()).isEqualTo("Category with name Food already exists");
    }

    @Test
    void should_delete_category() {
        CategoryEntity userCategory = new CategoryEntity();
        long idCategory = 1L;
        userCategory.setId(idCategory);
        userCategory.setName("Home");
        userCategory.setType(CategoryType.EXPENSE);
        userCategory.setOwnerType(CategoryOwnerType.USER);

        UserEntity user = new UserEntity();
        user.setId(1L);

        userCategory.setUser(user);
        when(authenticationFacade.getCurrentUserId()).thenReturn(1L);

        categoryRepository.save(userCategory);
        // when
        DeleteCategoryResponse response = categoryFacade.deleteCategory(idCategory);
        // then
        assertThat(response.id()).isEqualTo(idCategory);
        assertThrows(CategoryNotFoundException.class, () -> categoryFacade.getCategoryById(1L));
    }

    @Test
    void should_not_delete_system_category() {
        // given
        CategoryEntity systemCategory = new CategoryEntity();
        long idCategory = 1L;
        systemCategory.setId(idCategory);
        systemCategory.setName("Home");
        systemCategory.setType(CategoryType.EXPENSE);
        systemCategory.setOwnerType(CategoryOwnerType.SYSTEM);

        categoryRepository.save(systemCategory);
        // when && then
        CannotDeleteSystemCategoryException exception = assertThrows(CannotDeleteSystemCategoryException.class, () -> categoryFacade.deleteCategory(idCategory));
        assertThat(exception.getMessage()).isEqualTo("System category cannot be deleted");
    }

    @Test
    void should_not_delete_category_of_another_user() {
        CategoryEntity userCategory = new CategoryEntity();
        long idCategory = 1L;
        userCategory.setId(idCategory);
        userCategory.setName("Home");
        userCategory.setType(CategoryType.EXPENSE);
        userCategory.setOwnerType(CategoryOwnerType.USER);

        UserEntity user = new UserEntity();
        user.setId(1L);

        userCategory.setUser(user);
        when(authenticationFacade.getCurrentUserId()).thenReturn(2L);

        categoryRepository.save(userCategory);
        // when && then
        assertThrows(CategoryNotFoundException.class, () -> categoryFacade.deleteCategory(idCategory));
    }

    @Test
    void should_throw_exception_when_delete_not_existing_category() {
        CategoryEntity userCategory = new CategoryEntity();
        long idCategory = 1L;
        long notExistingCategory = 123L;
        userCategory.setId(idCategory);
        userCategory.setName("Home");
        userCategory.setType(CategoryType.EXPENSE);
        userCategory.setOwnerType(CategoryOwnerType.USER);

        UserEntity user = new UserEntity();
        user.setId(1L);

        userCategory.setUser(user);
        when(authenticationFacade.getCurrentUserId()).thenReturn(1L);

        categoryRepository.save(userCategory);
        // when && then
        assertThrows(CategoryNotFoundException.class, () -> categoryFacade.deleteCategory(notExistingCategory));
    }
}
