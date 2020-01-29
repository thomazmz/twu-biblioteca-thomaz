package com.twu.biblioteca.application;

import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import com.twu.biblioteca.domain.loanable.Loanable;
import com.twu.biblioteca.domain.loanable.LoanableService;

import java.util.Set;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;

public class ApplicationController {

    private LoanableService bookService;

    private LoanableService movieService;

    private ApplicationIO applicationIO;

    public ApplicationController(LoanableService bookService,
                                 LoanableService movieService,
                                 ApplicationIO applicationIO) {
        this.bookService = bookService;
        this.movieService = movieService;
        this.applicationIO = applicationIO;
    }

    private void listLoanables(Set<Loanable> loanables) {
        Set<Loanable> books = bookService.getAll();
        if(loanables.isEmpty())
            applicationIO.print("There are no items to list." + LINE_BREAK);
        books.forEach(book -> applicationIO.print(book + LINE_BREAK));
    }

    public void books() {
        applicationIO.print(LINE_BREAK + "BOOKS ( id | title | author| year )" + LINE_BREAK);
        Set<Loanable> books = bookService.getAll();
        this.listLoanables(books);
    }

    public void availableBooks() {
        applicationIO.print(LINE_BREAK + "AVAILABLE BOOKS ( id | title | author| year )" + LINE_BREAK);
        Set<Loanable> availableBooks = bookService.getAvailables();
        this.listLoanables(availableBooks);
    }

    public void bookCheckout() {
        applicationIO.print(LINE_BREAK + "Type the ID of the book you would like to check out: ");
        Long bookId = applicationIO.readLong();
        try {
            bookService.checkOut(bookId);
            applicationIO.print("Thank you! Enjoy the book" + LINE_BREAK);
        } catch (UnregisteredEntityIdException e) {
            applicationIO.print("Could not found a book with the given ID." + LINE_BREAK);
        } catch (UnavailableResourceException e) {
            applicationIO.print("Sorry, that book is not available." + LINE_BREAK);
        }
    }

    public void bookReturn() {
        applicationIO.print(LINE_BREAK + "Type the ID of the book you would like to return: ");
        Long bookId = applicationIO.readLong();
        try {
            bookService.checkIn(bookId);
            applicationIO.print("Thank you for returning the book." + LINE_BREAK);
        } catch (UnregisteredEntityIdException e) {
            applicationIO.print("Could not found a book with the given ID." + LINE_BREAK);
        } catch (UnavailableResourceException e) {
            applicationIO.print("That is not a valid book to return." + LINE_BREAK);
        }
    }

    public void availableMovies() {
        applicationIO.print(LINE_BREAK + "AVAILABLE MOVIES ( id | title | director | year | rating )" + LINE_BREAK);
        Set<Loanable> availableBooks = movieService.getAvailables();
        this.listLoanables(availableBooks);
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