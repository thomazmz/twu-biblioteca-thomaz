package com.twu.biblioteca.application;

import com.twu.biblioteca.application.menu.Menu;
import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.book.BookRepository;

import java.util.Set;

public class Application {

    private static String welcomeMessage = "Welcome!\n";

    private ApplicationInterface applicationInterface;

    private BookRepository bookRepository;

    public Application(BookRepository bookRepository, ApplicationInterface applicationInterface) {
        this.bookRepository = bookRepository;
        this.applicationInterface = applicationInterface;
    }

    public void start() {
        welcomeUser();
        renderMainMenu();
    }

    private void renderMainMenu() {
        Menu mainMenu = new Menu("Main Menu:", applicationInterface);
        mainMenu.putOption("1", "List all registered books", () -> {
            listBooks();
        });
        mainMenu.render();
    }

    private void welcomeUser() {
        applicationInterface.print(welcomeMessage);
    }

    private void listBooks() {
        Set<Book> books = bookRepository.getAll();
        books.forEach(book -> applicationInterface.print(book));
    }
}