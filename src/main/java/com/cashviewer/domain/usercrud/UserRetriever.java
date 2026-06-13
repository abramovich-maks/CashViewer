package com.cashviewer.domain.usercrud;

import com.cashviewer.domain.usercrud.dto.UserDtoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
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
                        .id(user.getId())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .enabled(user.isEnabled())
                        .authorities(user.getAuthorities())
                        .build());
    }

    boolean userExists(final String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    UserEntity findById(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}