package com.twu.biblioteca.application;

import com.twu.biblioteca.domain.book.BookRepository;
import com.twu.biblioteca.domain.book.BookService;

public class BookController {

    private ApplicationIO applicationIO;

    private BookRepository bookRepository;

    private BookService bookService;

    public BookController(ApplicationIO applicationIO, BookRepository bookRepository, BookService bookService) {
        this.applicationIO = applicationIO;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    };

    public void showAllBooks() {
        bookRepository.getAll().stream().forEach(book -> {
            applicationIO.print(book);
        });
    }

    public void showAllAvailableBooks() {
        bookRepository.getAvailableBooks().stream().forEach(book -> {
            applicationIO.print(book);
        });
    }
}
