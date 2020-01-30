package com.twu.biblioteca.domain.user;

import com.twu.biblioteca.domain.Repository;

import java.util.List;

public class UserRepository extends Repository<User> {

    public UserRepository(List<User> users) {
        super();
        users.forEach(user -> create(user));
    }

    public User findByEmail(String email) {
        return super.getAll()
                .stream().filter(user -> user.getEmail().equals(email))
                .findFirst()
                .get();
    }
}