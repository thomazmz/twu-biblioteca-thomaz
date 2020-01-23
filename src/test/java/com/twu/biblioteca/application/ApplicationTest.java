package com.twu.biblioteca.application;

import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.book.BookRepository;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ApplicationTest {

    private ApplicationInterface io;
    private BookRepository bookRepository;
    private Application biblioteca;

    @Before
    public void setUp() {
        io = mock(ApplicationInterface.class);
        bookRepository = new BookRepository();
        bookRepository.create(new Book("A Clockwork Orange", "Anthony Burgess", 1962));
        bookRepository.create(new Book("Brave New World", "Aldous Huxley", 1932));
        biblioteca = new Application(bookRepository, io);
    }

    @Test
    public void shouldPrintWelcomeMessage() {
        // When
        biblioteca.start();
        // Then
        verify(io).print("Welcome!\n");
    }
}