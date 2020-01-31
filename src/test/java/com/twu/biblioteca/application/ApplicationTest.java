package com.twu.biblioteca.application;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ApplicationTest {

    public Application application;

    public ApplicationIO applicationIO;

    @Before
    public void setUp() {
        // Given
        applicationIO = mock(ApplicationIO.class);
        application = new Application(applicationIO);
    }

    @Test
    public void shouldPrintWelcomeMessageWhenApplicationStarts() {
        // When
        when(applicationIO.readString()).thenReturn("Q");
        application.start();
        // Then
        verify(applicationIO, atLeastOnce()).print(Application.WELCOME_MESSAGE);
    }

    @Test
    public void shouldPrintMainMenuWhenApplicationStarts() {
        // When
        when(applicationIO.readString()).thenReturn("Q");
        application.start();
        // Then
        verify(applicationIO, atLeastOnce()).print(any(Menu.class));
    }

    @Test
    public void shouldPrintUserMenuWhenApplicationStarts() {

    }

    @Test
    public void shouldQuitTheApplicationWhenUserTypesTheQuitOption() {
        // When
        when(applicationIO.readString()).thenReturn("Q" );
        application.start();
        // Then
        verify(applicationIO, atLeast(1)).print(any(Menu.class));
    }
}