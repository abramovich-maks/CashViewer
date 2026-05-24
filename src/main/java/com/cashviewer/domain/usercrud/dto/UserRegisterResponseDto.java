package com.cashviewer.domain.usercrud.dto;

import com.cashviewer.domain.usercrud.UserEntity;
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
