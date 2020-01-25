package com.twu.biblioteca.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookRepositoryTest {

    public BookRepository bookRepository;

    public Book book1;
    public Book book2;

    @Before
    public void set_up() {
        // Given
        bookRepository = new BookRepository();
        book1 = new Book("Title1", "Author1", 2001);
        book2 = new Book("Title2", "Author2", 2002);
        bookRepository.create(book1);
        bookRepository.create(book2);
    }

    @Test
    public void should_return_only_available_books() {
        // When
        book1.checkOut();
        Set<Book> books = bookRepository.getAvailableBooks();
        // Then
        assertThat(books.size(), equalTo(1));
        assertThat(books.contains(book1), is(false));
        assertThat(books.contains(book2), is(true));
    }
}