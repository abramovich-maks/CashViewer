package com.cashviewer.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record UserDtoResponse(
        String email,
        String password
) {
}
