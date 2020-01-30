package com.twu.biblioteca.domain.user;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserTest {

    public static final String NAME = "userName";

    public static final String EMAIL = "userEmail";

    public static final String PASSWORD = "userPassword";

    public static final String PHONE_NUMBER = "userName";

    public User user;

    @Before
    public void setUp() {
        // Given
        this.user = new User(NAME, EMAIL, PASSWORD, PHONE_NUMBER);
    }

    @Test
    public void shouldReturnTitleAuthorAndYear() {
        // When
        String bookString = user.toString();
        // Then
        assertThat(bookString, containsString(NAME));
        assertThat(bookString, containsString(EMAIL));
        assertThat(bookString, containsString(PHONE_NUMBER));
    }

    @Test
    public void ShouldReturnTrueOnlyIfPasswordIsRightWhenVerifyPasswordIsCalled() {
        // When
        assertThat(user.checkPassword(PASSWORD), is(true));
        assertThat(user.checkPassword("!" + PASSWORD), is(false));
    }
}