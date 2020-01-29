package com.twu.biblioteca.domain.movie;

import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class MovieTest {

    public static String TITLE = "SomeTitle";
    public static String DIRECTOR = "SomeDirector";
    public static Year YEAR = Year.of(2000);
    public static Integer RATING = 10;

    public Movie movie;

    @Before
    public void setUp() {
        // Given
        movie = new Movie(TITLE, DIRECTOR, YEAR, RATING);
    }

    @Test
    public void shouldReturnTitleAuthorAndYear() {
        // When
        String bookString = movie.toString();
        // Then
        assertThat(bookString, containsString(TITLE));
        assertThat(bookString, containsString(DIRECTOR));
        assertThat(bookString, containsString(RATING.toString()));
        assertThat(bookString, containsString(YEAR.toString()));
    }
}