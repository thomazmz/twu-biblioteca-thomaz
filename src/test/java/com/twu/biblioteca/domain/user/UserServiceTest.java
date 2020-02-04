package com.twu.biblioteca.domain.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static com.twu.biblioteca.domain.user.UserTest.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    public UserService userService;

    public User user;

    @Mock
    public UserRepository userRepository;

    @Before
    public void setUp() {
        // Given
        userService = new UserService(userRepository);
        user = new User(LIBRARY_NUMBER, EMAIL, NAME, PASSWORD, PHONE_NUMBER);
    }

    @Test
    public void shouldTheCurrentUserBeNullWhenServiceIsInitialized() {
        // When
        Optional<User> currentUser = userService.getCurrentUser();
        // Then
        assertThat(currentUser.isPresent(), is(false));
    }

    @Test
    public void shouldReturnUserWhenCredentialsAreRight() throws WrongCredentialsException {
        // Given
        when(userRepository.findByLibraryNumber(LIBRARY_NUMBER)).thenReturn(Optional.of(user));
        // When
        User loggedUser = userService.login(LIBRARY_NUMBER, PASSWORD);
        // Then
        assertThat(loggedUser, is(user));
        assertThat(userService.getCurrentUser(), is(Optional.of(user)));
    }

    @Test(expected = WrongCredentialsException.class)
    public void shouldThrowWrongCredentialsExceptionWhenLibraryNumberisWrong() throws WrongCredentialsException {
        // Given
        when(userRepository.findByLibraryNumber(anyString())).thenReturn(Optional.empty());
        // When
        userService.login("!" + LIBRARY_NUMBER, PASSWORD);
        // Then : Should throw WrongCredentialsException
    }

    @Test(expected = WrongCredentialsException.class)
    public void shouldThrowWrongCredentialsExceptionWhenLPasswordisWrong() throws WrongCredentialsException {
        // Given
        when(userRepository.findByLibraryNumber(LIBRARY_NUMBER)).thenReturn(Optional.of(user));
        // When
        userService.login(LIBRARY_NUMBER, "!" + PASSWORD);
        // Then : Should throw WrongCredentialsException
    }
}