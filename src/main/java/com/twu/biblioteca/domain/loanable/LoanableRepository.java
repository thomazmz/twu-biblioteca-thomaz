package com.twu.biblioteca.domain.loanable;

import com.twu.biblioteca.domain.Repository;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class LoanableRepository<T extends Loanable> extends Repository<T> {

    public LoanableRepository() {
        super();
    }

    public Set<T> getAvailables() {
        return getAll()
                .stream()
                .filter(Loanable::isAvailable)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<T> getUnavailables() {
        return getAll()
                .stream()
                .filter(loanableItem -> !loanableItem.isAvailable())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}