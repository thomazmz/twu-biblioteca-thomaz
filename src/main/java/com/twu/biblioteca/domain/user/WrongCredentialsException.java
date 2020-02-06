package com.twu.biblioteca.domain.user;

public class WrongCredentialsException extends Exception {

    public static final String WRONG_CREDENTIALS_MESSAGE = "Wrong credentials.";

    public WrongCredentialsException() {
        super(WRONG_CREDENTIALS_MESSAGE);
    }
}
