package com.twu.biblioteca.domain.user;

import java.util.Optional;

public class UserService {

    private static User currentUser;

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        currentUser = null;
    }

    public Optional<User> getCurrentUser() {
        return Optional.ofNullable(currentUser);
    }

    public User loggin(String libraryNumber, String password) throws WrongCredentialsException {
        User user = userRepository.findByLibraryNumber(libraryNumber).orElseThrow(WrongCredentialsException::new);
        if(!user.checkPassword(password))
            throw new WrongCredentialsException();
        return currentUser = user;
    }
}