package com.twu.biblioteca.domain.movie;

import com.twu.biblioteca.domain.loanable.LoanableRepository;

import java.util.List;

public class MovieRepository extends LoanableRepository<Movie> {

    public MovieRepository(List<Movie> books) {
        super();
        books.forEach(book -> create(book));
    }
}
