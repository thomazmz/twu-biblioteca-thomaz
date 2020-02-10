package com.twu.biblioteca.application.book;

import com.twu.biblioteca.application.ApplicationIO;
import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.book.BookService;
import com.twu.biblioteca.domain.user.User;
import com.twu.biblioteca.domain.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;
import static com.twu.biblioteca.application.book.BookController.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @InjectMocks
    public BookController bookController;

    @Mock
    public ApplicationIO applicationIO;

    @Mock
    public BookService bookService;

    @Mock
    public UserService userService;

    @Mock
    public Book book;

    @Test
    public void shouldPrintAvailableBooks() {
        // Given
        Set<Book> items = new LinkedHashSet(Arrays.asList(book));
        when(bookService.getAvailables()).thenReturn(items);
        // When
        bookController.listAvailableBooks();
        // Then
        verify(applicationIO, times(1)).print(items);
    }

    @Test
    public void shouldPrintCheckoutSuccessMessageWhenBookIsCheckedOut() {
        // Given
        when(applicationIO.readLong()).thenReturn(1L);
        // When
        bookController.bookCheckout();
        // Then
        verify(applicationIO, times(1)).print(CHECKOUT_SUCCESS_MESSAGE + LINE_BREAK);
    }

    @Test
    public void shouldPrintNotFoundMessageWhenBookDoesNotExistForCheckout()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        when(applicationIO.readLong()).thenReturn(1L);
        when(bookService.checkOut(1L)).thenThrow(new UnregisteredEntityIdException());
        when(userService.getCurrentUser()).thenReturn(Optional.of(mock(User.class)));
        // When
        bookController.bookCheckout();
        // Then
        verify(applicationIO, times(1)).print(NOT_FOUND_MESSAGE + LINE_BREAK);
    }

    @Test
    public void shouldPrintCheckoutFailMessageWhenBookIsNotAvailableToCheckout()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        when(applicationIO.readLong()).thenReturn(1L);
        when(bookService.checkOut(1L)).thenThrow(new UnavailableResourceException());
        when(userService.getCurrentUser()).thenReturn(Optional.of(mock(User.class)));
        // When
        bookController.bookCheckout();
        // Then
        verify(applicationIO, times(1)).print(CHECKOUT_FAIL_MESSAGE + LINE_BREAK);
    }

    @Test
    public void shouldPrintCheckinSuccessMessageWhenBookIsCheckedIn()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        when(applicationIO.readLong()).thenReturn(1L);
        when(bookService.checkIn(1L)).thenReturn(mock(Book.class));
        when(userService.getCurrentUser()).thenReturn(Optional.of(mock(User.class)));
        // When
        bookController.bookReturn();
        // Then
        verify(applicationIO, times(1)).print(CHECKIN_SUCCESS_MESSAGE + LINE_BREAK);
    }

    @Test
    public void shouldPrintNotFoundMessageWhenBookDoesNotExistForCheckin()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        when(applicationIO.readLong()).thenReturn(1L);
        when(bookService.checkIn(1L)).thenThrow(new UnregisteredEntityIdException());
        when(userService.getCurrentUser()).thenReturn(Optional.of(mock(User.class)));
        // When
        bookController.bookReturn();
        // Then
        verify(applicationIO, times(1)).print(NOT_FOUND_MESSAGE + LINE_BREAK);
    }

    @Test
    public void shouldPrintCheckinFailMessageWhenBookIsNotAvailableToCheckin()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        when(applicationIO.readLong()).thenReturn(1L);
        when(bookService.checkIn(1L)).thenThrow(new UnavailableResourceException());
        when(userService.getCurrentUser()).thenReturn(Optional.of(mock(User.class)));
        // When
        bookController.bookReturn();
        // Then
        verify(applicationIO, times(1)).print(CHECKIN_FAIL_MESSAGE + LINE_BREAK);
    }

    @Test
    public void shouldPrintUserNotLoggedInMessageWhenUserIsNotLogedIn() throws UnregisteredEntityIdException {
        // Given
        when(bookService.getCurrentUserBorrowedBooks()).thenThrow(new UnregisteredEntityIdException());
        // When
        bookController.getCurrentUserBorrowedBooks();
        // Then
        verify(applicationIO, times(1)).print(contains(USER_NOT_LOGGED_IN_MESSAGE));
    }

    @Test
    public void shouldPrintUserBorrowedBooksWhenUserIsLoggedIn() throws UnregisteredEntityIdException {

        // Given
        Set<Book> books = new LinkedHashSet(Arrays.asList(book));
        when(bookService.getCurrentUserBorrowedBooks()).thenReturn(books);
        when(applicationIO.readLong()).thenReturn(1L);
        // When
        bookController.getCurrentUserBorrowedBooks();
        // Then
        verify(applicationIO, times(1)).print(books);
    }
}