package com.twu.biblioteca.application;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ApplicationIO {

    public static final String defaultErrorMessage = "Invalid input.";

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

    public static boolean isValidString(String str) {
        if(str != null && !str.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public String readString(){
        return scanner.next();
    }

    public Long readLong(String errorMessage){
        Long input = null;
        Boolean invalidInput = true;

        do {
            try {
                input = scanner.nextLong();
                invalidInput = false;
            } catch (Exception e) {
                print(errorMessage);
                scanner.next();
            }
        } while(invalidInput);
        return input;
    }
}