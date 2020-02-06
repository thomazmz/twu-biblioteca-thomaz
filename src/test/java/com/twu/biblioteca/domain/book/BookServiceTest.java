package com.twu.biblioteca.domain.book;

import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import com.twu.biblioteca.domain.borrowable.BorrowableItemRepository;
import com.twu.biblioteca.domain.user.User;
import com.twu.biblioteca.domain.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Year;
import java.util.Optional;

import static com.twu.biblioteca.domain.user.UserTest.*;
import static com.twu.biblioteca.domain.book.BookTest.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    public UserService userService;

    @Mock
    public BorrowableItemRepository<Book> bookRepository;

    public BookService bookServicce;

    public User user;

    public Book book;

    @Before
    public void setUp() {
        book = new Book(TITLE, AUTHOR, YEAR);
        user = new User(LIBRARY_NUMBER, NAME, EMAIL, PASSWORD, PHONE_NUMBER);
        bookServicce = new BookService(bookRepository, userService);
    }

    @Test
    public void shouldCheckOutBook() throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        when(bookRepository.getById(any(Long.class))).thenReturn(book);
        when(userService.getCurrentUser()).thenReturn(Optional.of(user));
        // When
        bookServicce.checkOut(book.getId());
        // Then
        assertThat(book.isAvailable(), is(false));
    }
}