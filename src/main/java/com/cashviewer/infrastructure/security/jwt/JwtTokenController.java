package com.cashviewer.infrastructure.security.jwt;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class JwtTokenController {

    private final JwtTokenGenerator tokenGenerator;


    @PostMapping("/token")
    public ResponseEntity<JwtResponseDto> generateAccessToken(@RequestBody @Valid TokenRequestDto dto) {
        String token = tokenGenerator.authenticateAndGenerateToken(dto.email(), dto.password());
        return ResponseEntity.ok(
                JwtResponseDto.builder()
                        .token(token)
                        .build());
    }

    @GetMapping("/test")
    public ResponseEntity<String> f() {
        return ResponseEntity.ok("test");
    }
}