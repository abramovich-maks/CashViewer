package com.cashviewer.infrastructure.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurrentUser {

    private Long id;
    private String email;
}