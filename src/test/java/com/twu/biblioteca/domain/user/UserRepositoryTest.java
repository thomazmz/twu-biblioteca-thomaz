package com.twu.biblioteca.domain.user;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static com.twu.biblioteca.domain.user.UserTest.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserRepositoryTest {

    public UserRepository userRepository;

    public User user;

    @Before
    public void setUp() {
        // Given
        user = new User(NAME, EMAIL, PASSWORD, PHONE_NUMBER);
        userRepository = new UserRepository(new LinkedList<>(Arrays.asList(user)));
    }

    @Test
    public void shouldFindUserByEmail() {
        // When
        User findedUser = userRepository.findByEmail(EMAIL);
        // Then
        assertThat(findedUser, is(user));
    }
}