package com.twu.biblioteca.domain.movie;

import com.twu.biblioteca.domain.borrowable.BorrowableItemRepository;

import java.util.List;

public class MovieRepository extends BorrowableItemRepository {
    public MovieRepository(List<Movie> movies) {
        super(movies);
    }
}