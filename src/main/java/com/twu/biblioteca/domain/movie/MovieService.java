package com.twu.biblioteca.domain.movie;

import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import com.twu.biblioteca.domain.borrowable.BorrowableItemRepository;
import com.twu.biblioteca.domain.borrowable.BorrowableItemService;
import com.twu.biblioteca.domain.user.User;
import com.twu.biblioteca.domain.user.UserService;

public class MovieService extends BorrowableItemService {

    private UserService userService;

    public MovieService(BorrowableItemRepository<Movie> borrowableItemRepository, UserService userService) {
        super(borrowableItemRepository);
        this.userService = userService;
    }

    public Movie checkOut(Long id) throws UnregisteredEntityIdException, UnavailableResourceException {
        User currentUser = userService.getCurrentUser().orElseThrow(UnregisteredEntityIdException::new);
        Movie movie = (Movie) super.checkOut(id, currentUser);
        return movie;
    }
}