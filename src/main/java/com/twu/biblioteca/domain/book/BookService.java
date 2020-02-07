package com.twu.biblioteca.domain.book;

import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import com.twu.biblioteca.domain.borrowable.BorrowableItemRepository;
import com.twu.biblioteca.domain.borrowable.BorrowableItemService;
import com.twu.biblioteca.domain.user.User;
import com.twu.biblioteca.domain.user.UserService;

import java.util.Set;

public class BookService extends BorrowableItemService<Book> {

    private UserService userService;

    public BookService(BorrowableItemRepository<Book> borrowableItemRepository, UserService userService) {
        super(borrowableItemRepository);
        this.userService = userService;
    }

    public Book checkOut(Long id) throws UnregisteredEntityIdException, UnavailableResourceException {
        User currentUser = userService.getCurrentUser().orElseThrow(UnregisteredEntityIdException::new);
        return super.checkOut(id, currentUser);
    }

    public Set<Book> getCurrentUserBorrowedBooks() throws UnregisteredEntityIdException {
        User currentUser = userService.getCurrentUser().orElseThrow(UnregisteredEntityIdException::new);
        return super.getItemsByBorrowerId(currentUser.getId());
    }
}