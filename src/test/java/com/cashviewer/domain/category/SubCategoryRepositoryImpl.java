package com.cashviewer.domain.category;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

class SubCategoryRepositoryImpl implements SubCategoryRepository {

    Map<Long, SubCategoryEntity> database = new ConcurrentHashMap<>();
    AtomicInteger index = new AtomicInteger(1);

    @Override
    public Optional<SubCategoryEntity> findAvailableSubCategoryByIdAndCategoryIdAndUserId(Long subCategoryId, Long categoryId, Long userId) {
        return database.values()
                .stream()
                .filter(subCategory ->
                        subCategory.getId().equals(subCategoryId) && subCategory.getCategory().getId().equals(categoryId) && (
                                subCategory.getOwnerType() == CategoryOwnerType.SYSTEM || (subCategory.getUser() != null && subCategory.getUser().getId().equals(userId))))
                .findFirst();
    }

    @Override
    public boolean existsSubCategoryByNameAndCategoryId(String subCategoryName, Long categoryId, Long userId) {
        return database.values()
                .stream()
                .anyMatch(subCategory ->
                        subCategory.getName().equals(subCategoryName) && subCategory.getCategory().getId().equals(categoryId) && (
                                subCategory.getOwnerType() == CategoryOwnerType.SYSTEM || (subCategory.getUser() != null && subCategory.getUser().getId().equals(userId))));
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends SubCategoryEntity> S saveAndFlush(final S entity) {
        return null;
    }

    @Override
    public <S extends SubCategoryEntity> List<S> saveAllAndFlush(final Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(final Iterable<SubCategoryEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(final Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public SubCategoryEntity getOne(final Long aLong) {
        return null;
    }

    @Override
    public SubCategoryEntity getById(final Long aLong) {
        return null;
    }

    @Override
    public SubCategoryEntity getReferenceById(final Long aLong) {
        return null;
    }

    @Override
    public <S extends SubCategoryEntity> Optional<S> findOne(final Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends SubCategoryEntity> List<S> findAll(final Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends SubCategoryEntity> List<S> findAll(final Example<S> example, final Sort sort) {
        return List.of();
    }

    @Override
    public <S extends SubCategoryEntity> Page<S> findAll(final Example<S> example, final Pageable pageable) {
        return null;
    }

    @Override
    public <S extends SubCategoryEntity> long count(final Example<S> example) {
        return 0;
    }

    @Override
    public <S extends SubCategoryEntity> boolean exists(final Example<S> example) {
        return false;
    }

    @Override
    public <S extends SubCategoryEntity, R> R findBy(final Example<S> example, final Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends SubCategoryEntity> S save(final S entity) {
        if (entity.getId() == null) {
            entity.setId((long) index.getAndIncrement());
        }
        database.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends SubCategoryEntity> List<S> saveAll(final Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<SubCategoryEntity> findById(final Long aLong) {
        return Optional.ofNullable(database.get(aLong));
    }

    @Override
    public boolean existsById(final Long aLong) {
        return false;
    }

    @Override
    public List<SubCategoryEntity> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public List<SubCategoryEntity> findAllById(final Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(final Long aLong) {

    }

    @Override
    public void delete(final SubCategoryEntity entity) {

    }

    @Override
    public void deleteAllById(final Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(final Iterable<? extends SubCategoryEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<SubCategoryEntity> findAll(final Sort sort) {
        return List.of();
    }

    @Override
    public Page<SubCategoryEntity> findAll(final Pageable pageable) {
        return null;
    }
}
