package com.twu.biblioteca.domain.borrowable;

import com.twu.biblioteca.domain.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class BorrowableItemTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    BorrowableItem borrowableItem;

    @Test
    public void shouldCheckInBookAfterCheckOut() {
        borrowableItem.checkOut(mock(User.class));
        assertThat(borrowableItem.isAvailable(), is(false));
        borrowableItem.checkIn();
        assertThat(borrowableItem.isAvailable(), is(true));
    }

    @Test
    public void shouldReturnBorrowerIfItemIsNotAvailable() {
        // Given
        borrowableItem.checkOut(mock(User.class));
        // When
        Optional<User> optionalUser = borrowableItem.getBorrower();
        // Then
        assertThat(optionalUser.isPresent(), is(true));
    }
}