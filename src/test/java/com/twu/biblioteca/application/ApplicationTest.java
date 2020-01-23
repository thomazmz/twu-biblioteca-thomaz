package com.twu.biblioteca.application;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ApplicationTest {

    private ApplicationIO applicationIO;
    private Application biblioteca;

    @Before
    public void setUp() {
        applicationIO = mock(ApplicationIO.class);
//        bookRepository = new BookRepository();
//        bookRepository.create(new Book("A Clockwork Orange", "Anthony Burgess", 1962));
//        bookRepository.create(new Book("Brave New World", "Aldous Huxley", 1932));
        biblioteca = new Application();
    }

    @Ignore
    @Test
    public void shouldPrintWelcomeMessage() {
        // When
        biblioteca.start();
        // Then
        verify(applicationIO).print("Welcome!\n");
    }

    @Ignore
    @Test
    public void ShouldQuitTheApplicationWhenChooseTheOptionTo() {
        // When
        biblioteca.start();
        // Then
        verify(applicationIO).print("Welcome!\n");
    }
}