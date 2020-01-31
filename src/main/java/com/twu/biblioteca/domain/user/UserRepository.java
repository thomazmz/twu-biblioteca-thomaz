package com.twu.biblioteca.domain.user;

import com.twu.biblioteca.domain.Repository;

import java.util.List;
import java.util.Optional;

public class UserRepository extends Repository<User> {

    public UserRepository(List<User> users) {
        super();
        users.forEach(user -> create(user));
    }

    public Optional<User> findByLibraryNumber(String libraryNumber) {
        return super.getAll()
                .stream().filter(user -> user.getLibraryNumber().equals(libraryNumber))
                .findFirst();
    }
}