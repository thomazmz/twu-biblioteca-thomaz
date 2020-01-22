package com.twu.biblioteca.application;

import com.twu.biblioteca.application.menu.Menu;
import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.book.BookRepository;

import java.util.Set;

public class Application {

    private static String welcomeMessage = "Welcome!\n";

    private ApplicationIO io;

    private BookRepository bookRepository;

    public Application(BookRepository bookRepository, ApplicationIO io) {
        this.bookRepository = bookRepository;
        this.io = io;
    }

    public Application(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.io = new ApplicationIO(System.in, System.out);
    }

    public void start() {
        welcomeUser();
        printMenu();
    }

    private void printMenu() {
        Menu menu = new Menu("Main Menu:", io);
        menu.putOption("1", "List all registered books", () -> {
            listBooks();
        });
        menu.print();
    }

    private void welcomeUser() {
        io.print(welcomeMessage);
    }

    private void listBooks() {
        Set<Book> books = bookRepository.getAll();
        books.forEach(book -> io.print(book));
    }
}