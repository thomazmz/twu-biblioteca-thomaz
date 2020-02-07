package com.twu.biblioteca.application;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MenuTest {

    public static final String HEADER = "Menu";
    public static final String SELECTOR = "1";
    public static final String SENTENCE = "Option";
    public static final Menu.Command COMMAND = mock(Menu.Command.class);

    Menu menu;

    @Before
    public void setUp() {
        // Given
        menu = new Menu(HEADER);
        menu.setOption(SELECTOR, SENTENCE, COMMAND);
    }

    @Test
    public void shouldContainHeaderSelectorsAndSentencesInMenuString() {
        // When
        String menuString = menu.toString();
        // Then
        assertThat(menuString, containsString(HEADER));
        assertThat(menuString, containsString(SELECTOR));
        assertThat(menuString, containsString(SENTENCE));
    }

    @Test
    public void shouldReturnOptionalOfCommandWhenExists() {
        // When
        Optional<Menu.Option> optional = menu.select(SELECTOR);
        // Then
        assertThat(optional.isPresent(), is(true));
    }

    @Test
    public void shouldReturnEmptyOptionalOfCommandWhenItDoesNotExists() {
        // When
        Optional<Menu.Option> optional = menu.select("InvalidSelector");
        // Then
        assertThat(optional.isPresent(), is(false));
    }

    @Test
    public void shouldPrintWarningWhenInvalidOptionIsSelectedOnMenu() {
        // When
        ApplicationIO applicationIO = mock(ApplicationIO.class);
        when(applicationIO.readString()).thenReturn( "2", "1" );
        menu.readInput(applicationIO);
        // Then
        verify(applicationIO, atLeastOnce()).print(Menu.INVALID_OPTION_MESSAGE);
    }
}