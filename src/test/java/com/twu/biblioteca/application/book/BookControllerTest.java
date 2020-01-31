package com.twu.biblioteca.application.book;

import com.twu.biblioteca.application.ApplicationIO;
import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.borrowable.BorrowableItemRepository;
import com.twu.biblioteca.domain.borrowable.BorrowableItemService;
import com.twu.biblioteca.domain.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;
import static com.twu.biblioteca.application.book.BookController.*;
import static com.twu.biblioteca.domain.book.BookTest.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @Mock
    public UserService userService;

    @Mock
    public ApplicationIO applicationIO;

    public Book book;

    public BorrowableItemService bookService;

    public BookController bookController;

    public BorrowableItemRepository<Book> bookRepository;

    @Before
    public void setUp() {
        // Given
        book = new Book(TITLE, AUTHOR, YEAR);
        bookRepository = new BorrowableItemRepository<>();
        bookService = new BorrowableItemService(bookRepository, userService);
        bookController = new BookController(bookService, applicationIO);
    }

    @Test
    public void shouldPrintAvailableBooks() {
        // Given
        bookRepository.create(book);
        // When
        bookController.availableBooks();
        // Then
        verify(applicationIO, times(1)).print(new LinkedHashSet(Arrays.asList(book)));
    }

    @Test
    public void shouldPrintCheckoutSuccessMessageWhenBookIsCheckedOut() {
        // Given
        bookRepository.create(book);
        when(applicationIO.readLong()).thenReturn(1L);
        // When
        bookController.bookCheckout();
        // Then
        verify(applicationIO, times(1)).print(CHECKOUT_SUCCESS_MESSAGE + LINE_BREAK);
    }

    @Test
    public void shouldPrintNotFoundMessageWhenBookDoesNotExistForCheckout() {
        // Given
        when(applicationIO.readLong()).thenReturn(1L);
        // When
        bookController.bookCheckout();
        // Then
        verify(applicationIO, times(1)).print(NOT_FOUND_MESSAGE + LINE_BREAK);
    }

    @Test
    public void shouldPrintCheckoutFailMessageWhenBookIsNotAvailableToCheckout() {
        // Given
        bookRepository.create(book);
        book.checkOut();
        when(applicationIO.readLong()).thenReturn(1L);
        // When
        bookController.bookCheckout();
        // Then
        verify(applicationIO, times(1)).print(CHECKOUT_FAIL_MESSAGE + LINE_BREAK);
    }

    @Test
    public void shouldPrintCheckinSuccessMessageWhenBookIsCheckedIn() {
        // Given
        bookRepository.create(book);
        book.checkOut();
        when(applicationIO.readLong()).thenReturn(1L);
        // When
        bookController.bookReturn();
        // Then
        verify(applicationIO, times(1)).print(CHECKIN_SUCCESS_MESSAGE + LINE_BREAK);
    }

    @Test
    public void shouldPrintNotFoundMessageWhenBookDoesNotExistForCheckin() {
        // Given
        when(applicationIO.readLong()).thenReturn(1L);
        // When
        bookController.bookReturn();
        // Then
        verify(applicationIO, times(1)).print(NOT_FOUND_MESSAGE + LINE_BREAK);
    }

    @Test
    public void shouldPrintCheckinFailMessageWhenBookIsNotAvailableToCheckin() {
        // Given
        bookRepository.create(book);
        when(applicationIO.readLong()).thenReturn(1L);
        // When
        bookController.bookReturn();
        // Then
        verify(applicationIO, times(1)).print(CHECKIN_FAIL_MESSAGE + LINE_BREAK);
    }
}