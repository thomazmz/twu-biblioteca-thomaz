package com.twu.biblioteca.domain.book;

import com.twu.biblioteca.domain.loanable.LoanableRepository;

import java.util.List;

public class BookRepository extends LoanableRepository<Book> {

    public BookRepository(List<Book> books) {
        super();
        books.forEach(book -> create(book));
    }
}