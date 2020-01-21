package com.twu.biblioteca.application;

import com.twu.biblioteca.Book;
import com.twu.biblioteca.application.Biblioteca;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class BibliotecaTest {

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
        Biblioteca app = new Biblioteca();
        String welcomeString = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
        // When
        app.start();
        // Then
        assertThat(provisoryOutputStream.toString(), containsString(welcomeString));
    }

    @Test
    public void listOfBooksMustBePrintedOutWhenAppIsStarted() {
        // Given
        List<Book> books = new ArrayList<>();
        books.add(new Book("Title", "Author", 2000));
        Biblioteca app = new Biblioteca(books);
        // When
        app.start();
        // Then
        assertThat(provisoryOutputStream.toString(), containsString("Title"));
        assertThat(provisoryOutputStream.toString(), containsString("Author"));
        assertThat(provisoryOutputStream.toString(), containsString("2000"));
    }
}