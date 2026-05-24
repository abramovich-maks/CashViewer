package com.cashviewer.infrastructure.security;

import com.cashviewer.domain.usercrud.EmailNotFoundException;
import com.cashviewer.domain.usercrud.UserFacade;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsManager {

    private final UserFacade userFacade;

    @Override
    public void createUser(final UserDetails user) {

    }

    @Override
    public void updateUser(final UserDetails user) {

    }

    @Override
    public void deleteUser(final String username) {

    }

    @Override
    public void changePassword(final @NonNull String oldPassword, final @NonNull String newPassword) {

    }

    @Override
    public boolean userExists(final @NonNull String email) {
        return userFacade.userExists(email);
    }

    @Override
    public UserDetails loadUserByUsername(final @NonNull String username) throws UsernameNotFoundException {
        return userFacade.findByEmail(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new EmailNotFoundException(username));
    }
}
