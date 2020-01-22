package com.twu.biblioteca.application;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ApplicationIO {

    private static PrintStream out;
    private static Scanner in;

    public ApplicationIO(InputStream inputStream, PrintStream printStream) {
        this.out = printStream;
        this.in = new Scanner(inputStream);
    }

    public void print(Object object) {
        out.print(object);
    }

    public String read(){
        return in.next();
    }
}
