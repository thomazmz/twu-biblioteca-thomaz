package com.twu.biblioteca.application.controllers;

import com.twu.biblioteca.application.ApplicationIO;
import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.book.BookRepository;

import java.util.Set;

public class BookController {

    private ApplicationIO applicationIO;

    private BookRepository bookRepository;

    public BookController(ApplicationIO applicationIO, BookRepository bookRepository) {
        this.applicationIO = applicationIO;
        this.bookRepository = bookRepository;
    }

    public void listAllBooks() {
            Set<Book> books = bookRepository.getAll();
            books.forEach(book -> applicationIO.print(book));
    }
}
