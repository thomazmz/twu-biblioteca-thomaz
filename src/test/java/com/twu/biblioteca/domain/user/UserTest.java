package com.twu.biblioteca.domain.user;

import com.twu.biblioteca.domain.book.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Set;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    public static final String LIBRARY_NUMBER = "000-0000";

    public static final String NAME = "userName";

    public static final String EMAIL = "userEmail";

    public static final String PASSWORD = "userPassword";

    public static final String PHONE_NUMBER = "userName";

    @Mock
    public Book book;

    public User user;

    @Before
    public void setUp() {
        // Given
        user = new User(LIBRARY_NUMBER, NAME, EMAIL, PASSWORD, PHONE_NUMBER);
    }

    @Test
    public void shouldReturnTitleAuthorAndYear() {
        // When
        String bookString = user.toString();
        // Then
        assertThat(bookString, containsString(NAME));
        assertThat(bookString, containsString(EMAIL));
        assertThat(bookString, containsString(PHONE_NUMBER));
    }

    @Test
    public void shouldReturnTrueOnlyIfPasswordIsRightWhenVerifyPasswordIsCalled() {
        // Then
        assertThat(user.checkPassword(PASSWORD), is(true));
        assertThat(user.checkPassword("!" + PASSWORD), is(false));
    }

    @Test
    public void shouldCheckOutBook() {
        // When
        user.checkOutABook(book);
        Set<Book> books = user.getBooksCheckedOut();
        // Then
        assertThat(books.contains(book), is(true));

    }
}