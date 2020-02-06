package com.twu.biblioteca.application.movie;

import com.twu.biblioteca.application.ApplicationIO;
import com.twu.biblioteca.domain.borrowable.BorrowableItemRepository;
import com.twu.biblioteca.domain.borrowable.BorrowableItemService;
import com.twu.biblioteca.domain.movie.Movie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.LinkedHashSet;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;
import static com.twu.biblioteca.application.movie.MovieController.*;
import static com.twu.biblioteca.domain.movie.MovieTest.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {

    @Mock
    public ApplicationIO applicationIO;

    public Movie movie;

    public BorrowableItemService movieService;

    public MovieController movieController;

    public BorrowableItemRepository<Movie> movieRepository;

    @Before
    public void setUp() {
        // Given
        movie = new Movie(TITLE, DIRECTOR, YEAR, RATING);
        movieRepository = new BorrowableItemRepository<>();
        movieService = new BorrowableItemService(movieRepository);
        movieController = new MovieController(movieService, applicationIO);
    }

    @Test
    public void shouldPrintAvailableMovies() {
        // Given
        movieRepository.create(movie);
        // When
        movieController.availableMovies();
        // Then
        verify(applicationIO, times(1)).print(new LinkedHashSet(Arrays.asList(movie)));
    }

    @Test
    public void shouldPrintCheckoutSuccessMessageWhenMovieIsCheckedOut() {
        // Given
        movieRepository.create(movie);
        when(applicationIO.readLong()).thenReturn(1L);
        // When
        movieController.movieCheckout();
        // Then
        verify(applicationIO, times(1)).print(CHECKOUT_SUCCESS_MESSAGE + LINE_BREAK);
    }

    @Test
    public void shouldPrintNotFoundMessageWhenMovieDoesNotExistForCheckout() {
        // Given
        when(applicationIO.readLong()).thenReturn(1L);
        // When
        movieController.movieCheckout();
        // Then
        verify(applicationIO, times(1)).print(NOT_FOUND_MESSAGE + LINE_BREAK);
    }

    @Test
    public void shouldPrintCheckoutFailMessageWhenMovieIsNotAvailableToCheckout() {
        // Given
        movieRepository.create(movie);
        movie.checkOut();
        when(applicationIO.readLong()).thenReturn(1L);
        // When
        movieController.movieCheckout();
        // Then
        verify(applicationIO, times(1)).print(CHECKOUT_FAIL_MESSAGE + LINE_BREAK);
    }
}