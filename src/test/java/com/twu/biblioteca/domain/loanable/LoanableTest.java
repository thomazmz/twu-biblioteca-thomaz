package com.twu.biblioteca.domain.loanable;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoanableTest {

    Loanable loanable = new LoanableImplementation();

    @Test
    public void shouldCheckInBookAfterCheckOut() {
        loanable.checkOut();
        assertThat(loanable.isAvailable(), is(false));
        loanable.checkIn();
        assertThat(loanable.isAvailable(), is(true));
    }

    public class LoanableImplementation extends Loanable {
    }

}