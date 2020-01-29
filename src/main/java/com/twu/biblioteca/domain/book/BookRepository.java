package com.twu.biblioteca.domain.book;

import com.twu.biblioteca.domain.borrowable.BorrowableItemRepository;

import java.util.List;

public class BookRepository extends BorrowableItemRepository<Book> {

    public BookRepository(List<Book> books) {
        super();
        books.forEach(book -> create(book));
    }
}