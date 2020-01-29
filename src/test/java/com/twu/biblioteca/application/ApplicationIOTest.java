package com.twu.biblioteca.application;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationIOTest {

    private ByteArrayOutputStream outputStream;

    private ApplicationIO applicationIO;

    @Before
    public void set_up() {
        applicationIO = new ApplicationIO();
    }

    @Test
    public void should_print_message() {
        // Given
        outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        applicationIO.setOutputStream(printStream);
        // When
        applicationIO.print("INPUT");
        String input = outputStream.toString();
        // Then
        assertThat(input, equalTo("INPUT"));
    }

    @Test
    public void should_read_string() {
        // Given
        Scanner scanner = new Scanner(new ByteArrayInputStream("INPUT".getBytes()));
        applicationIO.setScanner(scanner);
        // When
        String input = applicationIO.readString();
        // Then
        assertThat(input, equalTo("INPUT"));
    }

    @Test
    public void should_read_long() {
        // Given
        Scanner scanner = new Scanner(new ByteArrayInputStream("1".getBytes()));
        applicationIO.setScanner(scanner);
        // When
        Long input = applicationIO.readLong();
        // Then
        assertThat(input, equalTo(1L));
    }
}