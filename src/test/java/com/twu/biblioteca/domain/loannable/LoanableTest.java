package com.twu.biblioteca.domain.loannable;

import com.twu.biblioteca.domain.lonnable.Loanable;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoanableTest {

    Loanable loanable = new LoanableImplementation();

    @Test
    public void shouldCheckOutAndCheckInBook() {
        loanable.checkOut();
        assertThat(loanable.isAvailable(), is(false));
        loanable.checkIn();
        assertThat(loanable.isAvailable(), is(true));
    }
}