package com.cashviewer.infrastructure.security;

import com.cashviewer.domain.usercrud.dto.UserDtoResponse;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SecurityUser implements UserDetails {

    private final UserDtoResponse user;

    public SecurityUser(final UserDtoResponse user) {
        this.user = user;
    }

    @Override
    public @NullMarked Collection<? extends GrantedAuthority> getAuthorities() {
        return user.authorities()
                .stream()
                .map(authority -> (GrantedAuthority) () -> authority)
                .collect(Collectors.toList());
    }

    @Override
    public @Nullable String getPassword() {
        return user.password();
    }

    @Override
    public String getUsername() {
        return user.email();
    }

    public Long getId() {
        return user.id();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.enabled();
    }

    public List<String> getAuthoritiesAsString() {
        return user.authorities().stream().toList();
    }
}
