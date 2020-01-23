package com.twu.biblioteca.application;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ApplicationInterfaceTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void resetOutputStream() {
        outputStream.reset();
    }

    public InputStream getInputString(String string) {
        return new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    }

    public PrintStream getPrintStream() {
        return new PrintStream(outputStream);
    };

    @Test
    public void shouldPrintMessage() {
        String message = "Message";
        ApplicationInterface io = new ApplicationInterface(getInputString(""), getPrintStream());
        io.print(message);
        String printedMessage = outputStream.toString();
        assertThat(printedMessage, equalTo(message));
    }

    @Test
    public void shouldReadMessage() {
        String message = "Message";
        ApplicationInterface io = new ApplicationInterface(getInputString(message), getPrintStream());
        String readMessage = io.read();
        assertThat(readMessage, equalTo(message));
    }
}