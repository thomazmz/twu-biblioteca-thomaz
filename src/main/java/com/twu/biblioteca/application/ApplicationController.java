package com.twu.biblioteca.application;

import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.BookRepository;

import java.util.Optional;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;

public class ApplicationController {

    private BookRepository bookRepository;

    private ApplicationIO applicationIO;

    public ApplicationController(BookRepository bookRepository, ApplicationIO applicationIO) {
        this.bookRepository = bookRepository;
        this.applicationIO = applicationIO;
    }

    public void books() {
        applicationIO.print(LINE_BREAK + "BOOKS ( id | title | author| year )" + LINE_BREAK);
        bookRepository.getAll()
                .forEach(book -> applicationIO.print(book + LINE_BREAK));
    }

    public void availableBooks() {
        applicationIO.print(LINE_BREAK + "AVAILABLE BOOKS ( id | title | author| year )" + LINE_BREAK);
        bookRepository.getAvailableBooks()
                .forEach(book -> applicationIO.print(book + LINE_BREAK));
    }

    public void unavailableBooks() {
        applicationIO.print(LINE_BREAK + "UNAVAILABLE BOOKS ( id | title | author| year )" + LINE_BREAK);
        bookRepository.getUnAvailableBooks()
                .forEach(book -> applicationIO.print(book + LINE_BREAK));
    }

    public void bookCheckout() {
        applicationIO.print(LINE_BREAK + "Type the ID of the book you would like to check out: ");
        Optional<Long> userInputOptional = applicationIO.readLong();
        if(!userInputOptional.isPresent()) {
            applicationIO.print("Invalid book ID." + LINE_BREAK);
        } else {
            Optional<Book> bookOptional = bookRepository.getById(userInputOptional.get());
            if(!bookOptional.isPresent()) {
                applicationIO.print("Could not found a book with the given ID." + LINE_BREAK);
            } else {
                Book book = bookOptional.get();
                if(!book.isAvailableForCheckOut()) {
                    applicationIO.print("Sorry, that book is not available." + LINE_BREAK);
                } else {
                    book.checkOut();
                    applicationIO.print("Thank you! Enjoy the book" + LINE_BREAK);
                }
            }
        }
    }

    public void bookReturn() {
        applicationIO.print(LINE_BREAK + "Type the ID of the book you would like to return: ");
        Optional<Long> userInputOptional = applicationIO.readLong();
        if(!userInputOptional.isPresent()) {
            applicationIO.print("Invalid book ID." + LINE_BREAK);
        } else {
            Optional<Book> bookOptional = bookRepository.getById(userInputOptional.get());
            if(!bookOptional.isPresent()) {
                applicationIO.print("Could not found a book with the given ID." + LINE_BREAK);
            } else {
                Book book = bookOptional.get();
                if(book.isAvailableForCheckOut()) {
                    applicationIO.print("That is not a valid book to return." + LINE_BREAK);
                } else {
                    book.checkIn();
                    applicationIO.print("Thank you for returning the book." + LINE_BREAK);
                }
            }
        }
    }
}
