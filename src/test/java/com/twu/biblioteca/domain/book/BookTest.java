package com.twu.biblioteca.domain.book;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void shouldResturnTitleAuthorAndPublicationYear() {
        // Given
        String title = "Title";
        String author = "Author";
        Integer publicationYear = 2000;
        Book book = new Book(title, author, publicationYear);
        // When
        String bookString = book.toString();
        // Then
        assertThat(bookString, containsString("Title"));
        assertThat(bookString, containsString("Author"));
        assertThat(bookString, containsString(publicationYear.toString()));
    }
}