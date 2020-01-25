package com.twu.biblioteca.application;

import com.twu.biblioteca.domain.BookRepository;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;

public class ApplicationController {

    private static final String BOOKS_HEADER = "---- BOOKS ---------------------------------------------------------------";
    private static final String AVAILABLE_BOOKS_HEADER = "---- AVAILABLE BOOKS -----------------------------------------------------";

    private BookRepository bookRepository;
    private ApplicationIO applicationIO;

    public ApplicationController(BookRepository bookRepository, ApplicationIO applicationIO) {
        this.bookRepository = bookRepository;
        this.applicationIO = applicationIO;
    }

    public void listBooks() {
        applicationIO.print(LINE_BREAK + BOOKS_HEADER + LINE_BREAK);
        bookRepository.getAll()
                .forEach(book -> applicationIO.print(book + LINE_BREAK));
    }

    public void listAvailableBooks() {
        applicationIO.print(LINE_BREAK + AVAILABLE_BOOKS_HEADER + LINE_BREAK);
        bookRepository.getAvailableBooks()
                .forEach(book -> applicationIO.print(book + "\n"));
    }

}
