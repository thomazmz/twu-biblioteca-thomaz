package com.twu.biblioteca.application.user;

import com.twu.biblioteca.application.ApplicationIO;
import com.twu.biblioteca.domain.user.UserService;
import com.twu.biblioteca.domain.user.WrongCredentialsException;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;

public class UserController {

    public static final String HEADER = "LOGIN";

    public static final String LOGIN_LIBRARY_NUMBER_SENTENCE = "Enter your library number ";

    private static final String LOGIN_PASSWORD_SENTENCE = "Enter your password: ";

    UserService userService;

    private ApplicationIO applicationIO;

    public UserController(UserService userService, ApplicationIO applicationIO) {
        this.userService = userService;
        this.applicationIO = applicationIO;
    }

    public void login() {
        applicationIO.print(LINE_BREAK + HEADER + LINE_BREAK);
        try {
            userService.login(getUserLibraryNumber(), getUserPassword());
        } catch (WrongCredentialsException e) {
            applicationIO.print(e.getMessage() + LINE_BREAK);
        }
    }

    private String getUserLibraryNumber() {
        applicationIO.print(LOGIN_LIBRARY_NUMBER_SENTENCE);
        return applicationIO.readString();
    }

    private String getUserPassword() {
        applicationIO.print(LOGIN_PASSWORD_SENTENCE);
        return applicationIO.readString();
    }
}
