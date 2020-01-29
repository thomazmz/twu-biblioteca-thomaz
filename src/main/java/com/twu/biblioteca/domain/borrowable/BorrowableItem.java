package com.twu.biblioteca.domain.borrowable;

import com.twu.biblioteca.domain.Entity;

public abstract class BorrowableItem extends Entity {

    private Boolean available;

    public BorrowableItem() {
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
