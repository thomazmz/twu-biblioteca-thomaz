package com.twu.biblioteca.domain.book;

import com.twu.biblioteca.domain.borrowable.BorrowableItemRepository;

import java.util.List;

public class BookRepository extends BorrowableItemRepository {
    public BookRepository(List<Book> books) {
        super(books);
    }
}