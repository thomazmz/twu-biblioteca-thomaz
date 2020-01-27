package com.twu.biblioteca.domain.movie;

import com.twu.biblioteca.domain.LoanableRepository;

import java.util.List;

public class MovieRepository extends LoanableRepository<Movie> {

    public MovieRepository() {
        super();
    }

    public MovieRepository(List<Movie> books) {
        super();
        books.forEach(book -> create(book));
    }
}
