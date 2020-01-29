package com.twu.biblioteca.domain.movie;

import com.twu.biblioteca.domain.borrowable.BorrowableItemRepository;

import java.util.List;

public class MovieRepository extends BorrowableItemRepository<Movie> {

    public MovieRepository(List<Movie> books) {
        super();
        books.forEach(book -> create(book));
    }
}
