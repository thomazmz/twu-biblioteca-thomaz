package com.twu.biblioteca.application;

import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.book.BookRepository;
import com.twu.biblioteca.domain.loanable.LoanableService;
import com.twu.biblioteca.domain.movie.Movie;
import com.twu.biblioteca.domain.movie.MovieRepository;

import java.time.Year;
import java.util.Arrays;
import java.util.LinkedList;

public class Application {

    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";

    private static Boolean isAlive = true;

    private Menu menu;

    private ApplicationIO applicationIO;

    private ApplicationController applicationController;

    public Application() {

        BookRepository bookRepository = new BookRepository(new LinkedList<>(Arrays.asList(
                new Book("Refactoring", "Martin Fowller", Year.of(1999)),
                new Book("Effective Java", "Joshua Bloch", Year.of(2001)),
                new Book("Extreme Programming", "Kent Beck", Year.of(1999)),
                new Book("The Pragmatic Programmer", "Andrew Hunt", Year.of(1999)),
                new Book("Practices of an Agile Developer", "Venkat Subramaniam", Year.of(2006)),
                new Book("Clean Code", "Robbert C. Martin", Year.of(2008)),
                new Book("Test Driven Development By Example", "Kent Beck", Year.of(2000)),
                new Book("Database Management Systems", "Raghu Ramakrishnan", Year.of(1996)),
                new Book("Practical Unit Testing", "Tomek Kaczanowski", Year.of(2019)),
                new Book("Building Microservices", "Sam Newman", Year.of(2014)),
                new Book("Designing Event Driven Systems", "Ben Stopford", Year.of(2018)),
                new Book("Building Evolutionary Architectures1", "Rebecca Parsons", Year.of(2017))
        )));

        MovieRepository movieRepository = new MovieRepository(new LinkedList<>(Arrays.asList(
                new Movie("The Seven Samurai", "Akira Kurosawa", Year.of(1955), 10),
                new Movie("Reservoir Dogs", "Quentin Tarantino", Year.of(1992), 10),
                new Movie("Pan's Labyrinth", "Guillermo del Toro", Year.of(2006), 10),
                new Movie("The Deer Hunter", "Michael Cimino", Year.of(1978), 10),
                new Movie("Rocky", "John G. Avildsen", Year.of(1976), 10),
                new Movie("Memento", "Christopher Nolan", Year.of(2000), 10),
                new Movie("Die Hard", "John McTiernan", Year.of(1988), 10),
                new Movie("Ghostbusters", "Ivan Reitman", Year.of(1984), 10)
        )));

        LoanableService bookService = new LoanableService(bookRepository);

        LoanableService movieService = new LoanableService(movieRepository);

        applicationIO = new ApplicationIO();

        applicationController = new ApplicationController(bookService, movieService, applicationIO);

        menu = new Menu("Main Menu");
        menu.setOption("1", "Show books", applicationController::books);
        menu.setOption("2", "Show available books", applicationController::availableBooks);
        menu.setOption("3", "Checkout a book", applicationController::bookCheckout);
        menu.setOption("4", "Return a book", applicationController::bookReturn);
        menu.setOption("5", "Show available movies", applicationController::availableMovies);
        menu.setOption("6", "Checkout a movie", applicationController::movieCheckout);
        menu.setOption("Q", "Quit application", this::kill);

    }

    public Application(ApplicationIO applicationIO) {
        this();
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