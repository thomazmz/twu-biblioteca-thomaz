package com.twu.biblioteca.domain;

public abstract class Loanable extends Entity{

    private Boolean available;

    public Loanable() {
        this.available = true;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void checkOut() {
        available = false;
    }

    public void checkIn() {
        available = true;
    }
}
