package com.twu.biblioteca.domain.movie;


import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import com.twu.biblioteca.domain.borrowable.BorrowableItemRepository;
import com.twu.biblioteca.domain.user.User;
import com.twu.biblioteca.domain.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @InjectMocks
    public MovieService movieService;

    @Mock
    public UserService userService;

    @Mock
    public BorrowableItemRepository<Movie> movieRepository;

    @Mock
    public User user;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    public Movie movie;

    @Test
    public void shouldCheckOutBook() throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        when(movieRepository.getById(any(Long.class))).thenReturn(movie);
        when(userService.getCurrentUser()).thenReturn(Optional.of(user));
        // When
        movieService.checkOut(movie.getId());
        // Then
        assertThat(movie.isAvailable(), is(false));
    }
}