package com.twu.biblioteca.domain.book;

import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class BookTest {

    public static final String TITLE = "SomeTitle";
    public static final String AUTHOR = "SomeAuthor";
    public static final Year YEAR = Year.of(2000);

    public Book book;

    @Before
    public void setUp() {
        // Given
        book = new Book(TITLE, AUTHOR, YEAR);
    }

    @Test
    public void shouldReturnTitleAuthorAndYear() {
        // When
        String bookString = book.toString();
        // Then
        assertThat(bookString, containsString(TITLE));
        assertThat(bookString, containsString(AUTHOR));
        assertThat(bookString, containsString(YEAR.toString()));
    }
}