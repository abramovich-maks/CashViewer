package com.cashviewer.domain.usercrud;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long userId) {
        super(String.format("User with id %d not found", userId));
    }
}
