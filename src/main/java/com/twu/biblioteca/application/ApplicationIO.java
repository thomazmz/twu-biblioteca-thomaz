package com.twu.biblioteca.application;

import java.io.PrintStream;
import java.util.Scanner;

public class ApplicationIO {

    public static final String LINE_BREAK = "\n";

    public  static final String INVALID_INTEGER_MESSAGE = "You must enter a integer number. Try again: ";

    private Scanner scanner;

    private PrintStream outputStream;

    public ApplicationIO() {
        scanner = new Scanner(System.in);
        outputStream = System.out;
    }

    public void setOutputStream(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void print(Object object) {
        outputStream.print(object);
    }

    public String readString(){
        return scanner.nextLine();
    }

    public Long readLong() {
        try {
            String input = this.readString();
            return Long.parseLong(input);
        } catch (Exception e) {
            this.print(INVALID_INTEGER_MESSAGE);
            return this.readLong();
        }
    }
}