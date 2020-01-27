package com.twu.biblioteca.domain.book;

import com.twu.biblioteca.domain.LoanableRepository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookRepository extends LoanableRepository<Book> {

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
                .filter(book -> book.isAvailable())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<Book> getUnAvailableBooks() {
        return getAll()
                .stream()
                .filter(book -> !book.isAvailable())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}