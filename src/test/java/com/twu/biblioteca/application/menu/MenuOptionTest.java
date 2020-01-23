package com.twu.biblioteca.application.menu;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MenuOptionTest {

    @Test
    public void testToString() {
        // Given
        String sentence = "Sentence";
        MenuOption menuOption = new MenuOption(sentence, () -> {});
        // When
        String toStringResult = menuOption.toString();
        // Then
        assertThat(toStringResult, equalTo(sentence));
    }
}
