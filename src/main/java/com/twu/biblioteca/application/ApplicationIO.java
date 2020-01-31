package com.twu.biblioteca.application;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Scanner;

public class ApplicationIO {

    public static final String LINE_BREAK = "\n";

    public static final String INVALID_INTEGER_MESSAGE = "You must enter a integer number. Try again: ";

    public static final String EMPTY_COLLECTION_MESSAGE =  "There are no items to show.";

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

    public void print(Collection collection) {
        if(collection.isEmpty())
            print(EMPTY_COLLECTION_MESSAGE + LINE_BREAK);
        collection.forEach(item -> print(item + LINE_BREAK));
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