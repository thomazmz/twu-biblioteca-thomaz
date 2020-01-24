package com.twu.biblioteca.application.menu;

import com.twu.biblioteca.application.ApplicationIO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.security.InvalidParameterException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MenuTest {

    private ApplicationIO applicationIO;

    private MenuOption menuOption;

    private Menu menu;

    @Before
    public void setUp() {
        // Given
        applicationIO = mock(ApplicationIO.class);
        menu = new Menu("Options:", applicationIO);
        menu.putOption("1", "OptionOne", () -> { });
    }

    @Test
    public void shouldReturnMenuString() {
        // Given
        String expectedString = "Options:\n1. OptionOne\n";
        // When
        String menuString = menu.toString();
        // Then
        assertThat(menuString, equalTo(expectedString));
    }

    @Test
    public void shouldPrintMenuOptions() {
        // Given
        when(applicationIO.readString()).thenReturn("1");
        // When
        menu.render();
        // Then
        verify(applicationIO, atLeast(1)).print(menu.toString());
    }

    @Test
    public void shouldExecuteSelectedMenuOption() {
        // Given
        when(applicationIO.readString()).thenReturn("2");
        menuOption = mock(MenuOption.class);
        menu.putOption("2", menuOption);
        // When
        menu.render();
        // Then
        verify(menuOption, atLeast(1)).execute();
    }

    @Test
    public void shouldNotifyUserWhenInvalidOptionIsSelected() {
        // Given
        when(applicationIO.readString()).then(new Answer() {
            private int count = 0;
            public Object answer(InvocationOnMock invocation) {
                if (++count == 1) return "2";
                return "1";
            }
        });
        // When
        menu.render();
        // Then
        verify(applicationIO, atLeastOnce()).print("Invalid input.\n");
    }

    @Test(expected = InvalidParameterException.class)
    public void shouldRaiseInvalidParameterExceptionWhenSelectorIsNull() {
        // Given
        menu = new Menu("Instruction", applicationIO);
        menu.putOption(null, "Instruction", () -> {});
    }

    @Test(expected = InvalidParameterException.class)
    public void shouldRaiseInvalidParameterExceptionWhenSelectorIsEmpty() {
        // Given
        menu = new Menu("Instruction", applicationIO);
        menu.putOption("", "Instruction", () -> {});
    }
}