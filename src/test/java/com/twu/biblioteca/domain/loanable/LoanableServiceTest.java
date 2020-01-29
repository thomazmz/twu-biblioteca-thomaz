package com.twu.biblioteca.domain.loanable;

import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.omg.CORBA.Any;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoanableServiceTest {

    @Mock
    LoanableRepository loanableRepository;

    LoanableService loanableService;

    @Before
    public void setUp() {
        // Given
        loanableService = new LoanableServiceImplementation(loanableRepository);
    }

    @Test
    public void shouldThrowUnavailableResourceExceptionWhenCheckingOutIsNotPossible()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        Loanable loanable = mock(Loanable.class);
        when(loanable.isAvailable()).thenReturn(false);
        when(loanableRepository.getById(anyLong())).thenReturn(loanable);
        // When
        loanableService.checkIn(anyLong());
    }

    @Test(expected = UnavailableResourceException.class)
    public void shouldThrowUnavailableResourceExceptionWhenCheckingInIsNotPossible()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        Loanable loanable = mock(Loanable.class);
        when(loanable.isAvailable()).thenReturn(true);
        when(loanableRepository.getById(anyLong())).thenReturn(loanable);
        // When
        loanableService.checkIn(anyLong());
    }

    @Test
    public void shouldCheckoutBook()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        Loanable loanable = mock(Loanable.class);
        when(loanable.isAvailable()).thenReturn(true);
        when(loanableRepository.getById(anyLong())).thenReturn(loanable);
        // When
        loanableService.checkOut(anyLong());
        // Then
        assertThat(loanable.isAvailable(), is(true));
    }

    @Test
    public void shouldCheckInBook()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        Loanable loanable = mock(Loanable.class);
        when(loanable.isAvailable()).thenReturn(false);
        when(loanableRepository.getById(anyLong())).thenReturn(loanable);
        // When
        loanableService.checkIn(anyLong());
        // Then
        assertThat(loanable.isAvailable(), is(false));
    }

    public class LoanableServiceImplementation extends LoanableService {
        public LoanableServiceImplementation(LoanableRepository loanableRepository) {
            super(loanableRepository);
        }
    }
}