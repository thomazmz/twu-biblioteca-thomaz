package com.twu.biblioteca.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoanableTest {

    Loanable loanable = new LoanableImplementation();

    @Test
    public void should_check_out_and_check_in_book() {
        loanable.checkOut();
        assertThat(loanable.isAvailable(), is(false));
        loanable.checkIn();
        assertThat(loanable.isAvailable(), is(true));
    }
}