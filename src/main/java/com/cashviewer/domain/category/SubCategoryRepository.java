package com.cashviewer.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {

    @Query("""
                SELECT s
                FROM SubCategoryEntity s
                WHERE s.id = :subCategoryId
                  AND s.category.id = :categoryId
                  AND (
                        s.ownerType = 'SYSTEM'
                        OR s.user.id = :userId
                  )
            """)
    Optional<SubCategoryEntity> findAvailableSubCategoryByIdAndCategoryIdAndUserId(Long subCategoryId, Long categoryId, Long userId);

    @Query("""
                SELECT COUNT(c) > 0
                FROM SubCategoryEntity c
                WHERE c.name = :subCategoryName
                  AND c.category.id = :categoryId
                  AND (
                        c.ownerType = 'SYSTEM'
                        OR c.user.id = :userId
                  )
            """)
    boolean existsSubCategoryByNameAndCategoryId(String subCategoryName, Long categoryId, Long userId);
}