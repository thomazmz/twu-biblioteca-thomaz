package com.twu.biblioteca.domain;

public class UnregisteredEntityIdException extends Exception {

    public UnregisteredEntityIdException() {
        super("Unregistered entity id");
    }
}