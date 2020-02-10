package com.twu.biblioteca.application.movie;

import com.twu.biblioteca.application.ApplicationIO;
import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.borrowable.BorrowableItem;
import com.twu.biblioteca.domain.borrowable.BorrowableItemService;
import com.twu.biblioteca.domain.movie.Movie;
import com.twu.biblioteca.domain.movie.MovieService;

import java.util.Set;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;

public class MovieController {

    public static final String  HEADER = "MOVIES ( id | title | director| year )";

    public static final String CHECKOUT_INSTRUCTION = "Type the ID of the movie you would like to checkout: ";

    public static final String CHECKOUT_SUCCESS_MESSAGE = "Thank you! Enjoy the movie!";

    public static final String CHECKOUT_FAIL_MESSAGE = "Sorry, that movie is not available.";

    public static final String NOT_FOUND_MESSAGE = "Could not found a movie with the given ID.";

    private MovieService movieService;

    private ApplicationIO applicationIO;

    public MovieController(MovieService bookService,
                           ApplicationIO applicationIO) {
        this.movieService = bookService;
        this.applicationIO = applicationIO;
    }

    public void listMovies() {
        applicationIO.print(LINE_BREAK + HEADER + LINE_BREAK);
        Set<Movie> availableMovies = movieService.getAll();
        applicationIO.print(availableMovies);
    }

    public void listAvailableMovies() {
        applicationIO.print(LINE_BREAK + "AVAILABLE " + HEADER + LINE_BREAK);
        Set<Movie> availableBooks = movieService.getAvailables();
        applicationIO.print(availableBooks);
    }

    public void movieCheckout() {
        applicationIO.print(LINE_BREAK + CHECKOUT_INSTRUCTION);
        try {
            movieService.checkOut(applicationIO.readLong());
            applicationIO.print(CHECKOUT_SUCCESS_MESSAGE + LINE_BREAK);
        } catch (UnregisteredEntityIdException e) {
            applicationIO.print(NOT_FOUND_MESSAGE + LINE_BREAK);
        } catch (UnavailableResourceException e) {
            applicationIO.print(CHECKOUT_FAIL_MESSAGE + LINE_BREAK);
        }
    }
}