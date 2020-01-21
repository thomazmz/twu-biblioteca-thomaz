package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class BibliotecaAppTest {

    private final ByteArrayOutputStream provisoryOutputStream = new ByteArrayOutputStream();
    private final PrintStream originalOutputStream = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(provisoryOutputStream));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOutputStream);
    }

    @Test
    public void welcomeMessageMustBePrintedOutWhenAppIsStarted() {
        // Given
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        // When
        bibliotecaApp.start();
        // Then
        assertThat(provisoryOutputStream.toString(), containsString("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"));
    }

    @Test
    public void listOfBooksMustBePrintedOutWhenAppIsStarted() {
        // Given
        List<Book> books = new ArrayList<>();
        books.add(new Book("A Clockwork Orange"));
        books.add(new Book("Brave New World"));
        BibliotecaApp bibliotecaApp = new BibliotecaApp(books);
        // When
        bibliotecaApp.start();
        // Then
        assertThat(provisoryOutputStream.toString(), containsString("A Clockwork Orange"));
        assertThat(provisoryOutputStream.toString(), containsString("Brave New World"));
    }

}