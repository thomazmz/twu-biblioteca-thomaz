package com.twu.biblioteca.application.movie;

import com.twu.biblioteca.application.ApplicationIO;
import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import com.twu.biblioteca.domain.borrowable.BorrowableItemRepository;
import com.twu.biblioteca.domain.borrowable.BorrowableItemService;
import com.twu.biblioteca.domain.movie.Movie;
import com.twu.biblioteca.domain.movie.MovieService;
import com.twu.biblioteca.domain.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

    @InjectMocks
    public MovieController movieController;

    @Mock
    public ApplicationIO applicationIO;

    @Mock
    public MovieService movieService;

    @Mock
    public Movie movie;

    @Test
    public void shouldPrintAvailableMovies() {
        // Given
        when(movieService.getAvailables()).thenReturn(new LinkedHashSet(Arrays.asList(movie)));
        // When
        movieController.availableMovies();
        // Then
        verify(applicationIO, times(1)).print(new LinkedHashSet(Arrays.asList(movie)));
    }

    @Test
    public void shouldPrintCheckoutSuccessMessageWhenMovieIsCheckedOut()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        when(movieService.checkOut(1L)).thenReturn(movie);
        when(applicationIO.readLong()).thenReturn(1L);
        // When
        movieController.movieCheckout();
        // Then
        verify(applicationIO, times(1)).print(CHECKOUT_SUCCESS_MESSAGE + LINE_BREAK);
    }

    @Test
    public void shouldPrintNotFoundMessageWhenMovieDoesNotExistForCheckout()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        when(movieService.checkOut(1L)).thenThrow(new UnregisteredEntityIdException());
        when(applicationIO.readLong()).thenReturn(1L);
        // When
        movieController.movieCheckout();
        // Then
        verify(applicationIO, times(1)).print(NOT_FOUND_MESSAGE + LINE_BREAK);
    }

    @Test
    public void shouldPrintCheckoutFailMessageWhenMovieIsNotAvailableToCheckout()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        when(movieService.checkOut(1L)).thenThrow(new UnavailableResourceException());
        when(applicationIO.readLong()).thenReturn(1L);
        // When
        movieController.movieCheckout();
        // Then
        verify(applicationIO, times(1)).print(CHECKOUT_FAIL_MESSAGE + LINE_BREAK);
    }
}