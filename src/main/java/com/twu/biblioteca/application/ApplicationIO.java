package com.twu.biblioteca.application;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ApplicationIO {

    public static final String lineBreak = "\n";

    private Scanner scanner;

    private PrintStream outputStream;

    public ApplicationIO() {
        scanner = new Scanner(System.in);
        outputStream = System.out;
    }

    public ApplicationIO(InputStream inputStream, PrintStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.outputStream = outputStream;
    }

    public void print(Object object) {
        outputStream.print(object);
    }

    public String read(){
        return scanner.next();
    }
}