package com.twu.biblioteca.application.book;

import com.twu.biblioteca.application.ApplicationIO;
import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import com.twu.biblioteca.domain.loanable.Loanable;
import com.twu.biblioteca.domain.loanable.LoanableService;

import java.util.Set;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;
import static com.twu.biblioteca.application.loanable.LoanableUtils.listLoanables;

public class BookController {

    private LoanableService bookService;

    private ApplicationIO applicationIO;

    public BookController(LoanableService bookService,
                          ApplicationIO applicationIO) {
        this.bookService = bookService;
        this.applicationIO = applicationIO;
    }

    public void books() {
        applicationIO.print(LINE_BREAK + "BOOKS ( id | title | author| year )" + LINE_BREAK);
        Set<Loanable> books = bookService.getAll();
        listLoanables(books, applicationIO);
    }

    public void availableBooks() {
        applicationIO.print(LINE_BREAK + "AVAILABLE BOOKS ( id | title | author| year )" + LINE_BREAK);
        Set<Loanable> availableBooks = bookService.getAvailables();
        listLoanables(availableBooks, applicationIO);
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
}
