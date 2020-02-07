package com.twu.biblioteca.domain.borrowable;

import com.twu.biblioteca.domain.Entity;
import com.twu.biblioteca.domain.user.User;

import java.util.Optional;

public abstract class BorrowableItem extends Entity {

    private User borrower;

    public BorrowableItem() {
        this.borrower = null;
    }

    public Optional<User> getBorrower() {
        return Optional.ofNullable(borrower);
    }

    public Boolean isAvailable() {
        return borrower == null;
    }

    public void checkOut(User borrower) {
        this.borrower = borrower;
    }

    public void checkIn() {
        this.borrower = null;
    }
}
