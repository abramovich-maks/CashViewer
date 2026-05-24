package com.cashviewer.infrastructure.security.jwt;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class JwtTokenController {

    private final JwtTokenGenerator tokenGenerator;


    @PostMapping("/token")
    public ResponseEntity<JwtResponseDto> generateAccessToken(@RequestBody @Valid TokenRequestDto dto, HttpServletResponse response) {
        String token = tokenGenerator.generateAccessToken(dto.email(), dto.password());
        return ResponseEntity.ok(
                JwtResponseDto.builder()
                        .token(token)
                        .build());
    }
}