package com.twu.biblioteca.domain.borrowable;

import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BorrowableItemServiceTest {

    BorrowableItemServiceImplementation borrowableItemService;

    @Mock
    BorrowableItemRepository borrowableItemRepository;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    BorrowableItem borrowableItem;

    @Mock
    User user;

    @Before
    public void setUp() {
        // Given
        borrowableItemService = new BorrowableItemServiceImplementation();
    }

    @Test(expected = UnavailableResourceException.class)
    public void shouldThrowUnavailableResourceExceptionWhenCheckingOutIsNotPossible()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        borrowableItem.checkOut(user);
        when(borrowableItemRepository.getById(1L)).thenReturn(borrowableItem);
        // When
        borrowableItemService.checkOut(1L, user);
    }

    @Test(expected = UnavailableResourceException.class)
    public void shouldThrowUnavailableResourceExceptionWhenCheckingInIsNotPossible()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        when(borrowableItem.isAvailable()).thenReturn(true);
        when(borrowableItemRepository.getById(anyLong())).thenReturn(borrowableItem);
        // When
        borrowableItemService.checkIn(anyLong());
    }

    @Test
    public void shouldCheckout()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
         when(borrowableItemRepository.getById(1L)).thenReturn(borrowableItem);
        // When
        borrowableItemService.checkOut(1L, user);
        // Then
        assertThat(borrowableItem.isAvailable(), is(false));
    }

    @Test
    public void shouldCheckIn()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        when(borrowableItemRepository.getById(1L)).thenReturn(borrowableItem);
        borrowableItem.checkOut(mock(User.class));
        // When
        borrowableItemService.checkIn(1L);
        // Then
        assertThat(borrowableItem.isAvailable(), is(true));
    }

    @Test
    public void shouldReturnOnlyBooksBorrowedByUser() {
        // Given
        Book book = mock(Book.class);
        when(borrowableItemRepository.getItemsByBorrowerId(1L)).thenReturn(new LinkedHashSet(Arrays.asList(book)));
        // When
        Set<Book> borrowedBooks = borrowableItemService.getItemsByBorrowerId(1L);
        // Then
        assertThat(borrowedBooks.size(), is(1));
        assertThat(borrowedBooks.contains(book), is(true));
    }

    @Test
    public void shouldReturnAvailableBooks() {
        // Given
        Book book = mock(Book.class);
        when(borrowableItemRepository.getAvailables()).thenReturn(new LinkedHashSet(Arrays.asList(book)));
        // When
        Set<Book> borrowedBooks = borrowableItemService.getAvailables();
        // Then
        assertThat(borrowedBooks.size(), is(1));
        assertThat(borrowedBooks.contains(book), is(true));

    }

    public class BorrowableItemServiceImplementation extends BorrowableItemService {
        private BorrowableItemServiceImplementation() {
            super(borrowableItemRepository);
        }
    }
}