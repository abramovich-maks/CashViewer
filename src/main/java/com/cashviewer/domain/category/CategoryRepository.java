package com.cashviewer.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query("""
                SELECT DISTINCT c
                FROM CategoryEntity c
                LEFT JOIN FETCH c.subCategories sc
                WHERE (c.ownerType = 'SYSTEM'
                            OR c.user.id = :userId
                      )
                  AND (
                        sc IS NULL
                        OR sc.ownerType = 'SYSTEM'
                        OR sc.user.id = :userId
                      )
            """)
    List<CategoryEntity> findAllAvailableForUser(Long userId);

    @Query("""
                SELECT COUNT(c) > 0
                FROM CategoryEntity c
                WHERE c.name = :name
                  AND (
                        c.ownerType = 'SYSTEM'
                        OR c.user.id = :userId
                  )
            """)
    boolean existsAvailableCategoryByName(Long userId, String name);

    @Query("""
                SELECT DISTINCT c
                FROM CategoryEntity c
                LEFT JOIN FETCH c.subCategories sc
                WHERE c.id = :categoryId
                  AND (
                        c.ownerType = 'SYSTEM'
                        OR c.user.id = :userId
                  )
                  AND (
                        sc IS NULL
                        OR sc.ownerType = 'SYSTEM'
                        OR sc.user.id = :userId
                  )
            """)
    Optional<CategoryEntity> findAvailableCategoryForUser(Long categoryId, Long userId);
}