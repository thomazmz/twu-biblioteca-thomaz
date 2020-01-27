package com.twu.biblioteca.domain;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class LoanableRepository<T extends Loanable> extends Repository<T> {

    public LoanableRepository() {
        super();
    }

    public Set<T> getAvailables() {
        return getAll()
                .stream()
                .filter(entities -> entities.isAvailable())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<T> getUnavailables() {
        return getAll()
                .stream()
                .filter(book -> !book.isAvailable())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}