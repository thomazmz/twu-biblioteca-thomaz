package com.twu.biblioteca.application.user;

import com.twu.biblioteca.application.ApplicationIO;
import com.twu.biblioteca.domain.user.User;
import com.twu.biblioteca.domain.user.UserService;
import com.twu.biblioteca.domain.user.WrongCredentialsException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;
import static com.twu.biblioteca.application.user.UserController.*;
import static com.twu.biblioteca.domain.user.UserTest.LIBRARY_NUMBER;
import static com.twu.biblioteca.domain.user.UserTest.PASSWORD;
import static com.twu.biblioteca.domain.user.WrongCredentialsException.WRONG_CREDENTIALS_MESSAGE;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    public UserController userController;

    @Mock
    private ApplicationIO applicationIO;

    @Mock
    private UserService userService;

    @Test
    public void shouldAskUserForLibraryNumberAndPassword() {
        // Given
        when(userService.getCurrentUser()).thenReturn(Optional.of(mock(User.class)));
        when(applicationIO.readString()).thenReturn(LIBRARY_NUMBER, PASSWORD);
        // When
        userController.login();
        // Then
        verify(applicationIO, times(1)).print(contains(HEADER));
        verify(applicationIO, times(1)).print(contains(LOGIN_LIBRARY_NUMBER_SENTENCE));
        verify(applicationIO, times(1)).print(contains(LOGIN_PASSWORD_SENTENCE));
    }

    @Test
    public void shouldPrintFailMessageWhenUserTryToLogInWithWrongCredentials() throws WrongCredentialsException {
        // Given
        when(userService.login(any(String.class), any(String.class))).thenThrow(new WrongCredentialsException());
//        when(applicationIO.readString()).thenReturn(LIBRARY_NUMBER, PASSWORD);
        // When
        userController.login();
        // Then
        verify(applicationIO, times(1)).print(contains(WRONG_CREDENTIALS_MESSAGE));
    }
}