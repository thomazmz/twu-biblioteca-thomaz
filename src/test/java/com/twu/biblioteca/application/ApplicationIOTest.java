package com.twu.biblioteca.application;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ApplicationIOTest {

    private static final String messageString = "Message";

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private ApplicationIO applicationIO;

    @Before
    public void resetOutputStream() {
        // Given
        outputStream.reset();
        PrintStream printStream = new PrintStream(outputStream);
        InputStream inputStream = new ByteArrayInputStream(messageString.getBytes(StandardCharsets.UTF_8));
        applicationIO = new ApplicationIO(inputStream, printStream);
    }

    @Test
    public void shouldPrintMessage() {
        // When
        applicationIO.print(messageString);
        String printedMessage = outputStream.toString();
        // Then
        assertThat(printedMessage, equalTo(messageString));
    }

    @Test
    public void shouldReadMessage() {
        // When
        String readMessage = applicationIO.read();
        // Then
        assertThat(readMessage, equalTo(messageString));
    }
}