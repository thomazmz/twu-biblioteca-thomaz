package com.twu.biblioteca.domain.user;

public class WrongCredentialsException extends Exception {

    public WrongCredentialsException() {
        super("Wrong credentials.");
    }
}
