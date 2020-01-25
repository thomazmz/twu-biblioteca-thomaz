package com.twu.biblioteca.domain;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookRepository extends Repository<Book> {

    public BookRepository() {
        super();
    }

    public BookRepository(List<Book> books) {
        super();
        books.forEach(book -> create(book));
    }

    public Set<Book> getAvailableBooks() {
        return getAll()
                .stream()
                .filter(book -> book.isAvailableForCheckOut())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}