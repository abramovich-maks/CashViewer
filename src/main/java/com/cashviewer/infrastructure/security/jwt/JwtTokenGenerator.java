package com.cashviewer.infrastructure.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerator {

    public String generateAccessToken(String username, String password) {
        return "12345";
    }
}