package com.twu.biblioteca.domain.book;

import java.security.InvalidParameterException;
import java.util.Optional;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book checkoutBookById(Long id) throws InvalidParameterException {
        Optional<Book> bookOptional = bookRepository.findById(id);
        Book book = bookOptional.orElseThrow(() -> new InvalidParameterException("No Book found with property id=" + id));
        book.checkOut();
        return book;
    }

}
