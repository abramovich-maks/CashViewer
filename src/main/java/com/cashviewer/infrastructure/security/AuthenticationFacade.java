package com.cashviewer.infrastructure.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }

    private CurrentUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("Authentication is missing");
        }
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof CurrentUser currentUser)) {
            throw new IllegalStateException("Current user is missing");
        }

        return currentUser;
    }

}