package com.cashviewer.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {

    @Query("""
                SELECT COUNT(c) > 0
                FROM SubCategoryEntity  c
                WHERE c.category.name = :categoryName AND c.name = :subCategoryName
                  AND (
                        c.ownerType = 'SYSTEM'
                        OR c.user.id = :userId
                  )
            """)
    boolean existsAvailableSubCategoryByName(Long userId, String categoryName, String subCategoryName);
}