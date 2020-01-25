package com.twu.biblioteca.application;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ApplicationTest {

    public Application application;

    public ApplicationIO applicationIO;

    @Before
    public void set_up() {
        // Given
        applicationIO = mock(ApplicationIO.class);
        application = new Application(applicationIO);
    }

    @Test
    public void should_print_welcome_message_when_application_starts() {

    }

    @Test
    public void should_print_menu_when_application_starts() {

    }

    @Test
    public void should_print_warning_when_invalid_option_is_selected_on_menu() {

    }

    @Test
    public void should_only_terminate_when_user_types_the_quit_option() {

    }
}