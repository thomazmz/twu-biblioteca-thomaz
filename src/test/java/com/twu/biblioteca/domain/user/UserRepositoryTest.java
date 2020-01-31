package com.twu.biblioteca.domain.user;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

import static com.twu.biblioteca.domain.user.UserTest.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserRepositoryTest {

    public UserRepository userRepository;

    public User user;

    @Before
    public void setUp() {
        // Given
        user = new User(LIBRARY_NUMBER, NAME, EMAIL, PASSWORD, PHONE_NUMBER);
        userRepository = new UserRepository(new LinkedList<>(Arrays.asList(user)));
    }

    @Test
    public void shouldFindUserByEmail() {
        // When
        Optional<User> userOptional = userRepository.findByLibraryNumber(LIBRARY_NUMBER);
        Optional<User> emptyUserOptional = userRepository.findByLibraryNumber("");
        // Then
        assertThat(userOptional.get(), is(user));
        assertThat(emptyUserOptional.isPresent(), is(false));
    }
}