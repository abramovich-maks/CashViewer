package com.cashviewer.domain.loginandregister;

import com.cashviewer.domain.loginandregister.dto.UserDtoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

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

    public UserDtoResponse findByEmail(String email) {
        UserEntity userFound = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(email));
        return UserDtoResponse.builder()
                .email(userFound.getEmail())
                .password(userFound.getPassword())
                .build();
    }
}