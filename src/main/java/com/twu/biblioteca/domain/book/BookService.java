package com.twu.biblioteca.domain.book;

import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import com.twu.biblioteca.domain.borrowable.BorrowableItemRepository;
import com.twu.biblioteca.domain.borrowable.BorrowableItemService;
import com.twu.biblioteca.domain.user.User;
import com.twu.biblioteca.domain.user.UserService;

public class BookService extends BorrowableItemService {

    private UserService userService;

    public BookService(BorrowableItemRepository<Book> borrowableItemRepository, UserService userService) {
        super(borrowableItemRepository);
        this.userService = userService;
    }

    @Override
    public Book checkOut(Long id) throws UnregisteredEntityIdException, UnavailableResourceException {
        User user = userService.getCurrentUser().orElseThrow(UnregisteredEntityIdException::new);
        Book book = (Book) super.checkOut(id);
        user.checkOutABook(book);
        return book;
    }
}