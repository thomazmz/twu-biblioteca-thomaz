package com.twu.biblioteca.domain.book;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BookTest {

    public Book book;
    public String title = "Title";
    public String author = "Author";
    public Integer publicationYear = 2000;

    @Before
    public void setUp() {
        // Given
        book = new Book(title, author, publicationYear);
    }

    @Test
    public void shouldResturnTitleAuthorAndPublicationYear() {
        // When
        String bookString = book.toString();
        // Then
        assertThat(bookString, containsString("Title"));
        assertThat(bookString, containsString("Author"));
        assertThat(bookString, containsString(publicationYear.toString()));
    }

    @Test
    public void shouldCheckoutABook() {
        // When
        book.checkOut();
        // Then
        assertThat(book.isAvailable(), is(false));
    }

    @Test
    public void shouldCheckInABook() {
        // When
        book.checkOut();
        book.checkIn();
        // Then
        assertThat(book.isAvailable(), is(true));
    }
}