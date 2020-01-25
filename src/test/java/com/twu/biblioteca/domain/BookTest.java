package com.twu.biblioteca.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookTest {

    public static final String TITLE = "Title";
    public static final String AUTHOR = "Author";
    public static final Integer PUBLICATION_YEAR = 2000;

    public Book book;

    @Before
    public void set_up() {
        // Given
        book = new Book(TITLE, AUTHOR, PUBLICATION_YEAR);
    }

    @Test
    public void should_return_title_author_and_publication_year() {
        String bookString = book.toString();
        assertThat(bookString, containsString(TITLE));
        assertThat(bookString, containsString(AUTHOR));
        assertThat(bookString, containsString(PUBLICATION_YEAR.toString()));
    }

    @Test
    public void should_check_out_and_check_in_book() {
        book.checkOut();
        assertThat(book.isAvailableForCheckOut(), is(false));
        book.checkIn();
        assertThat(book.isAvailableForCheckOut(), is(true));
    }
}