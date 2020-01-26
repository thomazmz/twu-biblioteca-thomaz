package com.twu.biblioteca.application;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class ApplicationTest {

    public Application application;

    @Mock
    public ApplicationIO applicationIO;

    @Before
    public void set_up() {
        // Given
        applicationIO = mock(ApplicationIO.class);
        application = new Application(applicationIO);
    }

    @Test
    public void should_print_welcome_message_when_application_starts() {
        // When
        when(applicationIO.read()).thenReturn("Q");
        application.start();
        // Then
        verify(applicationIO, atLeastOnce()).print(Application.WELCOME_MESSAGE);
    }

    @Test
    public void should_print_menu_when_application_starts() {
        // When
        when(applicationIO.read()).thenReturn("Q");
        application.start();
        // Then
        verify(applicationIO, atLeastOnce()).print(any(Menu.class));
    }

    @Test
    public void should_quit_the_application_when_user_types_the_quit_option() {
        // When
        when(applicationIO.read()).thenReturn( "1", "Q" );
        application.start();
        // Then
        verify(applicationIO, atLeast(2)).print(any(Menu.class));
    }
}