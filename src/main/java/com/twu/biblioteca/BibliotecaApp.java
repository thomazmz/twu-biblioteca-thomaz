package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    private static String welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";

    List<Book> books;

    public BibliotecaApp() {
        this.books = new ArrayList<>();
    }

    public BibliotecaApp(List<Book> books) {
        this.books = books;
    }

    public void start() {
        welcomeUser();
        listBooks();
    }

    private void welcomeUser() {
        System.out.print(welcomeMessage);
    }

    private void listBooks() {
        for(Book book : books) {
            System.out.println(book.getTitle());
        }
    }

}
