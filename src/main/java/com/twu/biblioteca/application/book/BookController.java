package com.twu.biblioteca.application.book;

import com.twu.biblioteca.application.ApplicationIO;
import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import com.twu.biblioteca.domain.borrowable.BorrowableItem;
import com.twu.biblioteca.domain.borrowable.BorrowableItemService;

import java.util.Set;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;

public class BookController {

    public static final String  HEADER = "BOOKS ( id | title | author| year )";

    public static final String CHECKOUT_INSTRUCTION = "Type the ID of the book you would like to check out: ";

    public static final String CHECKOUT_SUCCESS_MESSAGE = "Thank you! Enjoy the book";

    public static final String CHECKOUT_FAIL_MESSAGE = "Sorry, that book is not available.";

    public static final String CHECKIN_INSTRUCTION = "Type the ID of the book you would like to return: ";

    public static final String CHECKIN_SUCCESS_MESSAGE = "Thank you for returning the book.";

    public static final String CHECKIN_FAIL_MESSAGE = "That is not a valid book to return.";

    public static final String NOT_FOUND_MESSAGE = "Could not found a book with the given ID.";

    private BorrowableItemService bookService;

    private ApplicationIO applicationIO;

    public BookController(BorrowableItemService bookService,
                          ApplicationIO applicationIO) {
        this.bookService = bookService;
        this.applicationIO = applicationIO;
    }

    public void availableBooks() {
        applicationIO.print(LINE_BREAK + "AVAILABLE " + HEADER + LINE_BREAK);
        Set<BorrowableItem> availableBooks = bookService.getAvailables();
        applicationIO.print(availableBooks);
    }

    public void bookCheckout() {
        applicationIO.print(LINE_BREAK + CHECKOUT_INSTRUCTION);
        try {
            bookService.checkOut(applicationIO.readLong());
            applicationIO.print(CHECKOUT_SUCCESS_MESSAGE + LINE_BREAK);
        } catch (UnregisteredEntityIdException e) {
            applicationIO.print(NOT_FOUND_MESSAGE + LINE_BREAK);
        } catch (UnavailableResourceException e) {
            applicationIO.print(CHECKOUT_FAIL_MESSAGE + LINE_BREAK);
        }
    }

    public void bookReturn() {
        applicationIO.print(LINE_BREAK + CHECKIN_INSTRUCTION);
        try {
            bookService.checkIn(applicationIO.readLong());
            applicationIO.print(CHECKIN_SUCCESS_MESSAGE + LINE_BREAK);
        } catch (UnregisteredEntityIdException e) {
            applicationIO.print(NOT_FOUND_MESSAGE + LINE_BREAK);
        } catch (UnavailableResourceException e) {
            applicationIO.print(CHECKIN_FAIL_MESSAGE + LINE_BREAK);
        }
    }
}
