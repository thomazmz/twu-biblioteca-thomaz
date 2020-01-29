package com.twu.biblioteca.domain;

public class UnavailableResourceException extends Exception {

    public UnavailableResourceException() {
        super("The resource is not available");
    }
}