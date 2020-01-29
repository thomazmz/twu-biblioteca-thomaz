package com.twu.biblioteca.domain.borrowable;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BorrowableItemTest {

    BorrowableItem borrowableItem = new BorrowableItemImplementation();

    @Test
    public void shouldCheckInBookAfterCheckOut() {
        borrowableItem.checkOut();
        assertThat(borrowableItem.isAvailable(), is(false));
        borrowableItem.checkIn();
        assertThat(borrowableItem.isAvailable(), is(true));
    }

    public class BorrowableItemImplementation extends BorrowableItem {
    }

}