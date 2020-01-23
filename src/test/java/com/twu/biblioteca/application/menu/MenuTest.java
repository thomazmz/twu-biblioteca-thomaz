package com.twu.biblioteca.application.menu;

import com.twu.biblioteca.application.ApplicationIO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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
        menuOption = new MenuOption("OptionOne", () -> { });
        menu = new Menu("Options:", applicationIO);
        menu.putOption("1", menuOption);
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
        when(applicationIO.read()).thenReturn("1");
        // When
        menu.render();
        // Then
        verify(applicationIO, atLeast(1)).print(menu.toString());
    }

    @Test
    public void shouldExecuteSelectedMenuOption() {
        // Given
        when(applicationIO.read()).thenReturn("1");
        // When
        menu.render();
        // Then
        verify(applicationIO, atLeast(1)).print(menu.toString());
    }

    @Test
    public void shouldNotifyUserWhenInvalidOptionIsSelected() {
        // Given
        when(applicationIO.read()).then(new Answer() {
            private int count = 0;
            public Object answer(InvocationOnMock invocation) {
                if (++count == 1) return "2";
                return "1";
            }
        });
        // When
        menu.render();
        // Then
        verify(applicationIO, atLeastOnce()).print("Please, select a valid option!\n");
    }
}