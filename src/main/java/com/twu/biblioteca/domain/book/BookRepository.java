package com.twu.biblioteca.domain.book;

import com.twu.biblioteca.domain.Repository;

import java.util.Set;
import java.util.stream.Collectors;

public class BookRepository extends Repository<Book> {

    public Set<Book> getAvailableBooks() {
        return super.getAll().stream().filter(book -> book.isAvailable()).collect(Collectors.toSet());
    };
}