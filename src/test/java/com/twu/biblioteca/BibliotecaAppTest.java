package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        BibliotecaApp bibliotecaApp = BibliotecaApp.getInstance();
        // When
        bibliotecaApp.start();
        // Then
        assertThat(provisoryOutputStream.toString(), containsString("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n"));
    }
}