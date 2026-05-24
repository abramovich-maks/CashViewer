package com.cashviewer.infrastructure.security.jwt;

import lombok.Builder;

@Builder
public record JwtResponseDto(String token,
                             String error) {
}