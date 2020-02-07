package com.twu.biblioteca.domain.borrowable;

import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import com.twu.biblioteca.domain.user.User;

import java.util.Set;


public class BorrowableItemService {

    private BorrowableItemRepository<BorrowableItem> borrowableItemRepository;

    public BorrowableItemService(BorrowableItemRepository borrowableItemRepository) {
        this.borrowableItemRepository = borrowableItemRepository;
    }

    public Set<BorrowableItem> getAvailables() {
        return this.borrowableItemRepository.getAvailables();
    }

    public BorrowableItem checkOut(Long id, User borrower) throws UnregisteredEntityIdException, UnavailableResourceException {
        BorrowableItem borrowableItem = this.borrowableItemRepository.getById(id);

        if(!borrowableItem.isAvailable())
            throw new UnavailableResourceException();

        borrowableItem.checkOut(borrower);
        return borrowableItem;
    }

    public BorrowableItem checkOut(Long id) throws UnregisteredEntityIdException, UnavailableResourceException {
        BorrowableItem borrowableItem = this.borrowableItemRepository.getById(id);

        if(!borrowableItem.isAvailable())
            throw new UnavailableResourceException();

        borrowableItem.checkOut();
        return borrowableItem;
    }


    public BorrowableItem checkIn(Long id) throws UnregisteredEntityIdException, UnavailableResourceException {
        BorrowableItem borrowableItem = this.borrowableItemRepository.getById(id);

        if(borrowableItem.isAvailable())
            throw new UnavailableResourceException();

        borrowableItem.checkIn();
        return borrowableItem;
    }
}