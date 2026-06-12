package com.cashviewer.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query("""
                SELECT c
                FROM CategoryEntity c
                WHERE c.ownerType = 'SYSTEM'
                   OR c.user.id = :userId
            """)
    List<CategoryEntity> findAllAvailableForUser(Long userId);

    boolean existsByUserIdAndName(Long userId, String name);
}