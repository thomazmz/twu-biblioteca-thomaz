package com.twu.biblioteca.domain.borrowable;

import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import com.twu.biblioteca.domain.book.BookService;
import com.twu.biblioteca.domain.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BorrowableItemServiceTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    BorrowableItem borrowableItem;

    @Mock
    BorrowableItemRepository borrowableItemRepository;

    BorrowableItemService borrowableItemService;

    @Before
    public void setUp() {
        borrowableItemService = new BorrowableItemService(borrowableItemRepository);

    }

    @Test(expected = UnavailableResourceException.class)
    public void shouldThrowUnavailableResourceExceptionWhenCheckingOutIsNotPossible()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        borrowableItem.checkOut(mock(User.class));
        when(borrowableItemRepository.getById(1L)).thenReturn(borrowableItem);
        // When
        borrowableItemService.checkOut(1L, mock(User.class));
    }

    @Test(expected = UnavailableResourceException.class)
    public void shouldThrowUnavailableResourceExceptionWhenCheckingInIsNotPossible()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        BorrowableItem borrowableItem = mock(BorrowableItem.class);
        when(borrowableItem.isAvailable()).thenReturn(true);
        when(borrowableItemRepository.getById(anyLong())).thenReturn(borrowableItem);
        // When
        borrowableItemService.checkIn(anyLong());
    }

    @Test
    public void shouldCheckout() throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
         when(borrowableItemRepository.getById(1L)).thenReturn(borrowableItem);
        // When
        borrowableItemService.checkOut(1L, mock(User.class));
        // Then
        assertThat(borrowableItem.isAvailable(), is(false));
    }

    @Test
    public void shouldCheckIn() throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        when(borrowableItemRepository.getById(1L)).thenReturn(borrowableItem);
        borrowableItem.checkOut(mock(User.class));
        // When
        borrowableItemService.checkIn(1L);
        // Then
        assertThat(borrowableItem.isAvailable(), is(true));
    }
}