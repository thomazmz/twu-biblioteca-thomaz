package com.twu.biblioteca.application;

import com.twu.biblioteca.application.menu.Menu;
import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.book.BookRepository;
import com.twu.biblioteca.domain.book.BookService;

import static com.twu.biblioteca.application.ApplicationIO.lineBreak;

public class Application {

    private static Boolean isAlive = true;

    private static String welcomeMessage = "Welcome!\n";

    private ApplicationIO applicationIO = new ApplicationIO();

    private BookRepository bookRepository = new BookRepository();

    private BookService  bookService = new BookService(bookRepository);

    private BookController bookController = new BookController(applicationIO, bookRepository, bookService);

    private Menu mainMenu;

    public Application() {

        mainMenu = new Menu("Main Menu", applicationIO);
        mainMenu.putOption("1", "Show all books", bookController::showAllBooks);
        mainMenu.putOption("2", "Show available books", bookController::showAllAvailableBooks);
        mainMenu.putOption("Q", "Quit", this::kill);

        // Temporary
        bookRepository.create(new Book("A Clockwork Orange", "Anthony Burgess", 1962));
        bookRepository.create(new Book("Brave New World", "Aldous Huxley", 1932));

    }

    public void start() {
        applicationIO.print(welcomeMessage);
        while(Application.isAlive) {
            applicationIO.print(lineBreak);
            mainMenu.render();
        }
    }

    public void kill() {
        isAlive = !isAlive;
    }
}