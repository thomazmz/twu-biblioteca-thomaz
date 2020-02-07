package com.twu.biblioteca.application;

import com.twu.biblioteca.application.book.BookController;
import com.twu.biblioteca.application.movie.MovieController;
import com.twu.biblioteca.application.user.UserController;
import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.book.BookService;
import com.twu.biblioteca.domain.borrowable.BorrowableItemRepository;
import com.twu.biblioteca.domain.borrowable.BorrowableItemService;
import com.twu.biblioteca.domain.movie.Movie;
import com.twu.biblioteca.domain.movie.MovieService;
import com.twu.biblioteca.domain.user.User;
import com.twu.biblioteca.domain.user.UserRepository;
import com.twu.biblioteca.domain.user.UserService;

import java.lang.management.MonitorInfo;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";

    private static Boolean isAlive = true;

    private Menu mainMenu;
    private Menu userMenu;

    private ApplicationIO applicationIO;
    private UserService userService;

    public Application(List<User> users, List<Book> books, List<Movie> movies) {

        UserRepository userRepository = new UserRepository(users);
        BorrowableItemRepository<Movie> movieRepository = new BorrowableItemRepository<>(movies);
        BorrowableItemRepository<Book> bookRepository = new BorrowableItemRepository<>(books);

        userService = new UserService(userRepository);
        BookService bookService = new BookService(bookRepository, userService);
        MovieService movieService = new MovieService(movieRepository, userService);

        applicationIO = new ApplicationIO();

        UserController userController = new UserController(userService, applicationIO);
        BookController bookController = new BookController(bookService, applicationIO);
        MovieController movieController = new MovieController(movieService, applicationIO);

        mainMenu = new Menu("Main Menu");
        mainMenu.setOption("1", "List of Books", bookController::availableBooks);
        mainMenu.setOption("2", "List of Movies", movieController::availableMovies);
        mainMenu.setOption("3", "Login with library number", userController::login);
        mainMenu.setOption("Q", "Quit application", this::kill);

        userMenu = new Menu("User Menu");
        userMenu.setOption("1", "List of Books", bookController::availableBooks);
        userMenu.setOption("2", "Checkout a book", bookController::bookCheckout);
        userMenu.setOption("3", "Return a book", bookController::bookReturn);
        userMenu.setOption("4", "List of Movies", movieController::availableMovies);
        userMenu.setOption("5", "Checkout a movie", movieController::movieCheckout);
        userMenu.setOption("Q", "Quit application", this::kill);
    }

    public Application(ApplicationIO applicationIO) {
        this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        this.applicationIO = applicationIO;
    }

    public void start() {
        applicationIO.print(WELCOME_MESSAGE);
        while (isAlive)
            render();
    }

    private void render() {
        if(!userService.getCurrentUser().isPresent()) {
            renderMainMenu();
        } else {
            renderUserMenu();
        }
    }

    private void renderMainMenu() {
        applicationIO.print(mainMenu);
        mainMenu.readInput(applicationIO);
    }

    private void renderUserMenu() {
        applicationIO.print(userMenu);
        userMenu.readInput(applicationIO);
    }

    public void kill() {
        isAlive = !isAlive;
    }
}