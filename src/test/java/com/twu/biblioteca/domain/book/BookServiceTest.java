package com.twu.biblioteca.domain.book;

import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import com.twu.biblioteca.domain.borrowable.BorrowableItemRepository;
import com.twu.biblioteca.domain.user.User;
import com.twu.biblioteca.domain.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static com.twu.biblioteca.domain.user.UserTest.*;
import static com.twu.biblioteca.domain.book.BookTest.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @InjectMocks
    public BookService bookService;

    @Mock
    public UserService userService;

    @Mock
    public BorrowableItemRepository<Book> bookRepository;

    @Mock
    public User user;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    public Book book;

    @Test
    public void shouldCheckOutBook() throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        when(bookRepository.getById(any(Long.class))).thenReturn(book);
        when(userService.getCurrentUser()).thenReturn(Optional.of(user));
        // When
        bookService.checkOut(book.getId());
        // Then
        assertThat(book.isAvailable(), is(false));
    }

    @Test
    public void shouldReturnOnlyBooksCheckedOutByCurrentUser() throws UnregisteredEntityIdException {
        // Given
        when(user.getId()).thenReturn(1L);
        when(userService.getCurrentUser()).thenReturn(Optional.of(user));
        when(bookRepository.getItemsByBorrowerId(1L)).thenReturn(new LinkedHashSet(Arrays.asList(book)));
        // When
        Set<Book> borrowedBooks = bookService.getCurrentUserBorrowedBooks();
        // Then
        assertThat(borrowedBooks.size(), is(1));
        assertThat(borrowedBooks.contains(book), is(true));
    }
}