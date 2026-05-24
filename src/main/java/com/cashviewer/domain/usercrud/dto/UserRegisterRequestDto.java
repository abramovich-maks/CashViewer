package com.cashviewer.domain.usercrud.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import static com.cashviewer.infrastructure.api.validation.ValidationConstants.PASSWORD_MAX_SIZE;
import static com.cashviewer.infrastructure.api.validation.ValidationConstants.PASSWORD_MIN_SIZE;


@Builder
public record UserRegisterRequestDto(
        @NotNull(message = "{username.not.null}")
        @NotEmpty(message = "{username.not.empty}")
        String username,

        @NotNull(message = "{email.not.null}")
        @NotEmpty(message = "{email.not.empty}")
        @Email(message = "{email.email}")
        String email,

        @NotNull(message = "{password.not.null}")
        @NotEmpty(message = "{password.not.empty}")
        @Size(min = PASSWORD_MIN_SIZE, max = PASSWORD_MAX_SIZE, message = "{password.size}")
        String password
) {
}