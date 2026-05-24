package com.cashviewer.infrastructure.security;

import com.cashviewer.domain.usercrud.EmailNotFoundException;
import com.cashviewer.domain.usercrud.UserFacade;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserFacade userFacade;

    @Override
    public UserDetails loadUserByUsername(final @NonNull String username) throws UsernameNotFoundException {
        return userFacade.findByEmail(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new EmailNotFoundException(username));
    }
}
