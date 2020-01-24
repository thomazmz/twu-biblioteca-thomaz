package com.twu.biblioteca.domain.book;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BookServiceTest {

    public BookRepository bookRepository;
    public Set<Book> books;

    @Before
    public void setUp() {
        // Given
        bookRepository = new BookRepository();
    }

    @Test
    public void shouldCheckOutBookById() {
        // Given
        BookService bookService = new BookService(bookRepository);
        Book book1 = new Book("Title1", "Author1", 2001);
        Book book2 = new Book("Title2", "Author2", 2002);
        bookRepository.create(book1);
        bookRepository.create(book2);
        // When
        Book book = bookService.checkoutBookById(book2.getId());
        Set<Book> books = bookRepository.getAvailableBooks();
        // Then
        assertThat(books.size(), equalTo(1));
        assertThat(books.contains(book1), is(true));
        assertThat(books.contains(book2), is(false));
        assertThat(book.isAvailable(), is(false));
    }
}