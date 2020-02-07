package com.twu.biblioteca.domain.borrowable;

import com.twu.biblioteca.domain.Entity;
import com.twu.biblioteca.domain.user.User;

public abstract class BorrowableItem extends Entity {

    private Boolean available;

    private User borrower;

    public BorrowableItem() {
        this.available = true;
        this.borrower = null;
    }

    public Boolean isAvailable() {
        return borrower == null;
    }

    public void checkOut(User borrower) {
        this.borrower = borrower;
    }

    public void checkOut() {
        this.available = false;
    }

    public void checkIn() {
        this.borrower = null;
    }
}
