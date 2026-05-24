package com.cashviewer.domain.usercrud;

public class EmailNotFoundException extends RuntimeException {

    public final String userEmail;

    public EmailNotFoundException(String userEmail) {
        super(String.format("Email %s is not exist", userEmail));
        this.userEmail = userEmail;
    }
}