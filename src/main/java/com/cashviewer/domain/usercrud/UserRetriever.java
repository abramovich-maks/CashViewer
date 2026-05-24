package com.cashviewer.domain.usercrud;

import com.cashviewer.domain.usercrud.dto.UserDtoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Log4j2
@Component
class UserRetriever {

    private final UserRepository userRepository;

    public void checkEmailExists(final String email) {
        boolean emailExists = userRepository.existsByEmail(email);
        if (emailExists) {
            throw new UserAlreadyExistException(email);
        }
    }

    public Optional<UserDtoResponse> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> UserDtoResponse.builder()
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .enabled(user.isEnabled())
                        .authorities(user.getAuthorities())
                        .build());
    }

    boolean userExists(final String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}