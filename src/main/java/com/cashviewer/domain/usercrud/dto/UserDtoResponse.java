package com.cashviewer.domain.usercrud.dto;

import lombok.Builder;

import java.util.Collection;

@Builder
public record UserDtoResponse(
        String email,
        String password,
        boolean enabled,
        Collection<String> authorities
) {
}
