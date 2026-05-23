package com.cashviewer.domain.loginandregister;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);
}