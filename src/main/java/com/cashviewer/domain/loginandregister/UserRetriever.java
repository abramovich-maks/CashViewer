package com.cashviewer.domain.loginandregister;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Log4j2
class UserRetriever {

    private final UserRepository userRepository;

    public void checkEmailExists(final String email) {
        boolean emailExists = userRepository.existsByEmail(email);
        if (emailExists) {
            throw new UserAlreadyExistException(email);
        }
    }
}