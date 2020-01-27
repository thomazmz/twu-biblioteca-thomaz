package com.twu.biblioteca.application;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ApplicationIOTest {

    private static String inputString = "1";

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
        String givenInput = outputStream.toString();
        // Then
        assertThat(givenInput, equalTo(inputString));
    }

    @Test
    public void should_read_string() {
        // When
        String inputString = applicationIO.readString();
        // Then
        assertThat(inputString, equalTo(inputString));
    }

    @Test
    public void should_return_empty_optional_when_not_long() {
        // When
        String readMessage = applicationIO.readString();
        // Then
        assertThat(readMessage, equalTo(inputString));

    }

    @Test
    public void should_return_optional_when_long() {
        // When
        Optional<Long> inputOptional = applicationIO.readLong();
        // Then
        assertThat(inputOptional.get(), equalTo(1L));
    }

    @Test
    public void should_return_empty_optional_when_invalid_long() {
        // When
        inputString = "a";
        this.set_up();
        Optional<Long> inputOptional = applicationIO.readLong();
        // Then
        assertThat(inputOptional.isPresent(), equalTo(false));
    }
}