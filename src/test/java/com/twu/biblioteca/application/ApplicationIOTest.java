package com.twu.biblioteca.application;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ApplicationIOTest {

    private ByteArrayOutputStream outputStream;

    private ApplicationIO applicationIO;

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        applicationIO = new ApplicationIO();
    }

    @Test
    public void shouldPrintMessage() {
        // Given
        PrintStream printStream = new PrintStream(outputStream);
        applicationIO.setOutputStream(printStream);
        // When
        applicationIO.print("INPUT");
        String input = outputStream.toString();
        // Then
        assertThat(input, is("INPUT"));
    }

    @Test
    public void shouldPrintCollectionLineByLine() {
        // Given
        PrintStream printStream = new PrintStream(outputStream);
        applicationIO.setOutputStream(printStream);
        String firstElement = "firstElement";
        String secondElement = "secondElement";
        Collection<String> collection = new ArrayList<>();
        collection.add(firstElement);
        collection.add(secondElement);
        // When
        applicationIO.print(collection);
        String input = outputStream.toString();
        //Then
        assertThat(input, is(firstElement + "\n" + secondElement + "\n"));
    }

    @Test
    public void shouldPrintMessageWhenCollectionIsEmpty() {
        // Given
        PrintStream printStream = new PrintStream(outputStream);
        applicationIO.setOutputStream(printStream);
        Collection<String> collection = new ArrayList<>();
        // When
        applicationIO.print(collection);
        String input = outputStream.toString();
        //Then
        assertThat(input, is(ApplicationIO.EMPTY_COLLECTION_MESSAGE + LINE_BREAK));
    }

    @Test
    public void shouldReadString() {
        // Given
        Scanner scanner = new Scanner(new ByteArrayInputStream("INPUT".getBytes()));
        applicationIO.setScanner(scanner);
        // When
        String input = applicationIO.readString();
        // Then
        assertThat(input, is("INPUT"));
    }

    @Test
    public void shouldReadLongIntegers() {
        // Given
        Scanner scanner = new Scanner(new ByteArrayInputStream("1".getBytes()));
        applicationIO.setScanner(scanner);
        // When
        Long input = applicationIO.readLong();
        // Then
        assertThat(input, is(1L));
    }

    @Test
    public void shouldOnlyReadLongIntegers() {
        // Given
        ApplicationIO applicationIOSpy = Mockito.spy(applicationIO);
        doReturn("A").doReturn("1").when(applicationIOSpy).readString();
        // When
        Long input = applicationIOSpy.readLong();
        // Then
        assertThat(input, equalTo(1L));
        verify(applicationIOSpy, atLeastOnce()).print(ApplicationIO.INVALID_INTEGER_MESSAGE);
    }
}