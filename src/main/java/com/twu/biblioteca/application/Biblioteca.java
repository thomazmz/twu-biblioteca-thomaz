package com.twu.biblioteca.application;

import com.twu.biblioteca.Book;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private static String welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";

    private ApplicationIO io = ApplicationIO.getInstance();

    List<Book> books;

    public Biblioteca() {
        this.books = new ArrayList<>();
    }

    public Biblioteca(List<Book> books) {
        this.books = books;
    }

    public void start() {
        welcomeUser();
        listBooks();
    }

    private void welcomeUser() {
        io.print(welcomeMessage);
    }

    private void listBooks() {
        for(Book book : books) {
            io.print(book.getTitle() + " | " + book.getAuthor() + " | " + book.getPublicationYear());
        }
    }
}
