package com.twu.biblioteca.application;

import com.twu.biblioteca.application.book.BookController;
import com.twu.biblioteca.application.movie.MovieController;
import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.borrowable.BorrowableItemRepository;
import com.twu.biblioteca.domain.borrowable.BorrowableItemService;
import com.twu.biblioteca.domain.movie.Movie;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";

    private static Boolean isAlive = true;

    private Menu menu;

    private ApplicationIO applicationIO;

    private BookController bookController;

    private MovieController movieController;

    public Application(List<Book> books, List<Movie> movies) {

        BorrowableItemRepository bookRepository = new BorrowableItemRepository(books);
        BorrowableItemRepository movieRepository = new BorrowableItemRepository(movies);

        BorrowableItemService bookService = new BorrowableItemService(bookRepository);
        BorrowableItemService movieService = new BorrowableItemService(movieRepository);

        applicationIO = new ApplicationIO();
        bookController = new BookController(bookService, applicationIO);
        movieController = new MovieController(movieService, applicationIO);

        menu = new Menu("Main Menu");
        menu.setOption("1", "Show books", bookController::books);
        menu.setOption("2", "Show available books", bookController::availableBooks);
        menu.setOption("3", "Checkout a book", bookController::bookCheckout);
        menu.setOption("4", "Return a book", bookController::bookReturn);
        menu.setOption("5", "Show available movies", movieController::availableMovies);
        menu.setOption("6", "Checkout a movie", movieController::movieCheckout);
        menu.setOption("Q", "Quit application", this::kill);
    }

    public Application(ApplicationIO applicationIO) {
        this(new ArrayList<>(), new ArrayList<>());
        this.applicationIO = applicationIO;
    }

    public void start() {
        applicationIO.print(WELCOME_MESSAGE);
        while (isAlive)
            renderMenu();
    }

    private void renderMenu() {
        applicationIO.print(menu);
        menu.readInput(applicationIO);
    }

    public void kill() {
        isAlive = !isAlive;
    }
}