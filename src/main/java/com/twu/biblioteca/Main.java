package com.twu.biblioteca;

import com.twu.biblioteca.application.Application;
import com.twu.biblioteca.application.ApplicationInterface;
import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.book.BookRepository;

public class Main {

    public static void main(String[] args) {

        ApplicationInterface io = new ApplicationInterface(System.in, System.out);

        BookRepository bookRepository = new BookRepository();
        bookRepository.create(new Book("A Clockwork Orange", "Anthony Burgess", 1962));
        bookRepository.create(new Book("Brave New World", "Aldous Huxley", 1932));

        Application biblioteca = new Application(bookRepository, io);
        biblioteca.start();

    }
}
