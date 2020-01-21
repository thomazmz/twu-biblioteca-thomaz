package com.twu.biblioteca.application;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ApplicationIOTest {

    private final ByteArrayOutputStream temporaryOutputStream = new ByteArrayOutputStream();
    private final PrintStream originalOutputStream = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(temporaryOutputStream));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOutputStream);
    }

    @Test
    public void shouldPrintMessage() {
        // Given
        ApplicationIO applicationIO = ApplicationIO.getInstance();
        // When
        applicationIO.print("Message");
        // Then
        assertThat(temporaryOutputStream.toString(), equalTo("Message\n"));
    }
}