package com.twu.biblioteca.application;

import com.twu.biblioteca.application.controllers.BookController;
import com.twu.biblioteca.application.menu.Menu;
import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.book.BookRepository;

public class Application {

    private static String welcomeMessage = "Welcome!\n";

    private ApplicationIO applicationIO;

    private BookController bookController;

    public Application() {

        BookRepository bookRepository = new BookRepository();
        bookRepository.create(new Book("A Clockwork Orange", "Anthony Burgess", 1962));
        bookRepository.create(new Book("Brave New World", "Aldous Huxley", 1932));

        this.applicationIO = new ApplicationIO();
        this.bookController = new BookController(this.applicationIO, bookRepository);

    }

    public void start() {
        welcomeUser();
        renderMainMenu();
    }

    private void renderMainMenu() {
        Menu mainMenu = new Menu("Main Menu:", this.applicationIO);
        mainMenu.putOption("1", "List available books", bookController::listAllBooks);
        mainMenu.putOption("q", "Quit", () -> {});
        mainMenu.render();
    }

    private void welcomeUser() {
        applicationIO.print(welcomeMessage);
    }
}