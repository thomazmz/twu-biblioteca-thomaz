package com.twu.biblioteca.application.menu;

import com.twu.biblioteca.application.ApplicationIO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

public class MenuTest {

    private ApplicationIO io;
    private Menu menu;

    @Before
    public void setUp() {
        io = mock(ApplicationIO.class);
        menu = new Menu("Menu", io);
    }

    @Test
    public void shouldPrintOptions() {
        // Given
        Menu menu = new Menu("Options:", io);
        menu.putOption("1", "OptionOne", () -> { });
        menu.putOption("2", "OptionTwo", () -> { });
        when(io.read()).thenReturn("1");
        // When
        menu.print();
        // Then
        verify(io).print("Options:\n");
        verify(io).print("1. OptionOne\n");
        verify(io).print("2. OptionTwo\n");
    }

    @Test
    public void shouldExecuteSelectedOption() {
        // Given
        Menu menu = new Menu("Options:", io);
        MenuOption option = mock(MenuOption.class);
        menu.putOption("1", option);
        when(io.read()).thenReturn("1");
        // When
        menu.print();
        // Then
        verify(option, atLeast(1)).execute();
    }

    @Test
    public void shouldNotifyUserWhenInvalidOptionIsSelected() {
        // Given
        Menu menu = new Menu("Options:", io);
        MenuOption option = mock(MenuOption.class);
        menu.putOption("1", option);
        when(io.read()).then(new Answer() {
            private int count = 0;
            public Object answer(InvocationOnMock invocation) {
                if (++count == 1) return "2";
                return "1";
            }
        });
        // When
        menu.print();
        // Then
        verify(io, atLeastOnce()).print("Please, select a valid option!\n");

    }
}