package com.twu.biblioteca.application;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ApplicationIOTest {

    private static final String inputString = "Input";

    private ByteArrayOutputStream outputStream;

    private ApplicationIO applicationIO;

    @Before
    public void set_up() {
        // Given
        outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
        applicationIO = new ApplicationIO(inputStream, printStream);
    }

    @Test
    public void should_print_message() {
        // When
        applicationIO.print(inputString);
        String printedMessage = outputStream.toString();
        // Then
        assertThat(printedMessage, equalTo(inputString));
    }

    @Test
    public void should_read_message() {
        // When
        String readMessage = applicationIO.readString();
        // Then
        assertThat(readMessage, equalTo(inputString));
    }
}