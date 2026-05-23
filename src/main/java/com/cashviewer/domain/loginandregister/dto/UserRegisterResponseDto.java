package com.cashviewer.domain.loginandregister.dto;

import com.cashviewer.domain.loginandregister.UserEntity;
import lombok.Builder;

/**
 * DTO for {@link UserEntity}
 */

@Builder
public record UserRegisterResponseDto(
        Long id,
        String username,
        String email
) {
}
