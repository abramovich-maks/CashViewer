package com.cashviewer.domain.usercrud;

public class UserAlreadyExistException extends RuntimeException {

    public final String userEmail;

    public UserAlreadyExistException(String userEmail) {
        super(String.format("User with email [ %s ] already exists", userEmail));
        this.userEmail = userEmail;
    }
}
