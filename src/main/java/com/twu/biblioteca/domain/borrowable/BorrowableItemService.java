package com.twu.biblioteca.domain.borrowable;

import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;

import java.util.Set;


public class BorrowableItemService {

    private BorrowableItemRepository<BorrowableItem> borrowableItemRepository;

    public BorrowableItemService(BorrowableItemRepository borrowableItemRepository) {
        this.borrowableItemRepository = borrowableItemRepository;
    }

    public BorrowableItem getById(Long id) throws UnregisteredEntityIdException {
        return this.borrowableItemRepository.getById(id);
    }

    public Set<BorrowableItem> getAll() {
        return this.borrowableItemRepository.getAll();
    }

    public Set<BorrowableItem> getAvailables() {
        return this.borrowableItemRepository.getAvailables();
    }

    public Set<BorrowableItem> getUnavailables() {
        return this.borrowableItemRepository.getUnavailables();
    }

    public void checkOut(Long id) throws UnregisteredEntityIdException, UnavailableResourceException {
        BorrowableItem borrowableItem = this.borrowableItemRepository.getById(id);

        if(!borrowableItem.isAvailable())
            throw new UnavailableResourceException();

        borrowableItem.checkOut();
    }

    public void checkIn(Long id) throws UnregisteredEntityIdException, UnavailableResourceException {
        BorrowableItem borrowableItem = this.borrowableItemRepository.getById(id);

        if(borrowableItem.isAvailable())
            throw new UnavailableResourceException();

        borrowableItem.checkIn();
    }
}