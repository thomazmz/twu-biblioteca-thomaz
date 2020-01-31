package com.twu.biblioteca.domain.borrowable;

import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import com.twu.biblioteca.domain.user.UserService;

import java.util.Set;


public class BorrowableItemService {

    private BorrowableItemRepository<BorrowableItem> borrowableItemRepository;

    private UserService userService;

    public BorrowableItemService(BorrowableItemRepository borrowableItemRepository, UserService userService) {
        this.borrowableItemRepository = borrowableItemRepository;
        this.userService = userService;
    }

    public Set<BorrowableItem> getAll() {
        return this.borrowableItemRepository.getAll();
    }

    public Set<BorrowableItem> getAvailables() {
        return this.borrowableItemRepository.getAvailables();
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