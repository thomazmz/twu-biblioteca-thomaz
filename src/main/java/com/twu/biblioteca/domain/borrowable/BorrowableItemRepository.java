package com.twu.biblioteca.domain.borrowable;

import com.twu.biblioteca.domain.Repository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BorrowableItemRepository<T extends BorrowableItem> extends Repository<T> {

    public BorrowableItemRepository(List<T> borrowableItems) {
        super();
        borrowableItems.forEach(item -> create(item));
    }

    public BorrowableItemRepository() {
        super();
    }

    public Set<T> getAvailables() {
        return getAll()
                .stream()
                .filter(BorrowableItem::isAvailable)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}