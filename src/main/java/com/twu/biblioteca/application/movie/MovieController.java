package com.twu.biblioteca.application.movie;

import com.twu.biblioteca.application.ApplicationIO;
import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import com.twu.biblioteca.domain.loanable.Loanable;
import com.twu.biblioteca.domain.loanable.LoanableService;

import java.util.Set;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;
import static com.twu.biblioteca.application.loanable.LoanableUtils.listLoanables;

public class MovieController {

    private LoanableService movieService;

    private ApplicationIO applicationIO;

    public MovieController(LoanableService bookService,
                          ApplicationIO applicationIO) {
        this.movieService = bookService;
        this.applicationIO = applicationIO;
    }

    public void availableMovies() {
        applicationIO.print(LINE_BREAK + "AVAILABLE MOVIES ( id | title | director | year | rating )" + LINE_BREAK);
        Set<Loanable> availableBooks = movieService.getAvailables();
        listLoanables(availableBooks, applicationIO);
    }

    public void movieCheckout() {
        applicationIO.print(LINE_BREAK + "Type the ID of the movie you would like to checkout: ");
        Long movieId = applicationIO.readLong();
        try {
            movieService.checkOut(movieId);
            applicationIO.print("Thank you! Enjoy the movie" + LINE_BREAK);
        } catch (UnregisteredEntityIdException e) {
            applicationIO.print("Could not found a movie with the given ID." + LINE_BREAK);
        } catch (UnavailableResourceException e) {
            applicationIO.print("Sorry, that movie is not available." + LINE_BREAK);
        }
    }
}
