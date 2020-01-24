package com.twu.biblioteca.domain.book;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BookRepositoryTest {

    public BookRepository bookRepository;

    public Book book1;

    public Book book2;

    @Before
    public void setUp() {
        //Given
        bookRepository = new BookRepository();
        book1 = new Book("Title1", "Author1", 2001);
        book2 = new Book("Title2", "Author2", 2002);
        bookRepository.create(book1);
        bookRepository.create(book2);
    }

    @Test
    public void shouldReturnOnlyAvailableBooks() {
        // When
        book1.checkOut();
        Set<Book> books = bookRepository.getAvailableBooks();
        // Then
        assertThat(books.size(), equalTo(1));
        assertThat(books.contains(book1), is(false));
        assertThat(books.contains(book2), is(true));
    }

    @Test
    public void shouldReturnBookOptionalWhenFindingById() {
        // When
        Optional<Book> bookOptional1 = bookRepository.findById(1L);
        Optional<Book> bookOptional2 = bookRepository.findById(3L);
        Optional<Book> bookOptional3 = bookRepository.findById(null);
        // Then
        assertThat(book1, equalTo(bookOptional1.get()));
        assertThat(false, equalTo(bookOptional2.isPresent()));
        assertThat(false, equalTo(bookOptional3.isPresent()));
    }
}