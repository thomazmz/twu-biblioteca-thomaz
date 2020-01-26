package com.twu.biblioteca.application;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;

public class MenuTest {

    public static final String HEADER = "Menu";
    public static final String SELECTOR = "1";
    public static final String SENTENCE = "Option";
    public static final Menu.Command COMMAND = mock(Menu.Command.class);

    Menu menu;

    @Before
    public void set_up() {
        // Given
        menu = new Menu(HEADER);
        menu.setOption(SELECTOR, SENTENCE, COMMAND);
    }

    @Test
    public void should_contain_header_selectors_and_sentences_in_menu_string() {
        // When
        String menuString = menu.toString();
        // Then
        assertThat(menuString, containsString(HEADER));
        assertThat(menuString, containsString(SELECTOR));
        assertThat(menuString, containsString(SENTENCE));
    }

    @Test
    public void should_return_optional_of_command_when_exists() {
        // When
        Optional<Menu.Option> optional = menu.select(SELECTOR);
        // Then
        assertThat(optional.isPresent(), is(true));
    }

    @Test
    public void should_return_empty_optional_of_command_when_it_does_not_exists() {
        // When
        Optional<Menu.Option> optional = menu.select("InvalidSelector");
        // Then
        assertThat(optional.isPresent(), is(false));
    }

    @Test
    public void should_print_warning_when_invalid_option_is_selected_on_menu() {
        // When
        ApplicationIO applicationIO = mock(ApplicationIO.class);
        when(applicationIO.read()).thenReturn( "2", "1" );
        menu.readInput(applicationIO);
        // Then
        verify(applicationIO, atLeastOnce()).print(Menu.INVALID_OPTION_MESSAGE);
    }
}