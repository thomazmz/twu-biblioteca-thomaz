package com.twu.biblioteca.domain.borrowable;

import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BorrowableItemServiceTest {

    @Mock
    BorrowableItemRepository borrowableItemRepository;

    BorrowableItemService borrowableItemService;

    @Before
    public void setUp() {
        // Given
        borrowableItemService = new BorrowableItemServiceImplementation(borrowableItemRepository);
    }

    @Test
    public void shouldThrowUnavailableResourceExceptionWhenCheckingOutIsNotPossible()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        BorrowableItem borrowableItem = mock(BorrowableItem.class);
        when(borrowableItem.isAvailable()).thenReturn(false);
        when(borrowableItemRepository.getById(anyLong())).thenReturn(borrowableItem);
        // When
        borrowableItemService.checkIn(anyLong());
    }

    @Test(expected = UnavailableResourceException.class)
    public void shouldThrowUnavailableResourceExceptionWhenCheckingInIsNotPossible()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        BorrowableItem borrowableItem = mock(BorrowableItem.class);
        when(borrowableItem.isAvailable()).thenReturn(true);
        when(borrowableItemRepository.getById(anyLong())).thenReturn(borrowableItem);
        // When
        borrowableItemService.checkIn(anyLong());
    }

    @Test
    public void shouldCheckoutBook()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        BorrowableItem borrowableItem = mock(BorrowableItem.class);
        when(borrowableItem.isAvailable()).thenReturn(true);
        when(borrowableItemRepository.getById(anyLong())).thenReturn(borrowableItem);
        // When
        borrowableItemService.checkOut(anyLong());
        // Then
        assertThat(borrowableItem.isAvailable(), is(true));
    }

    @Test
    public void shouldCheckInBook()
            throws UnregisteredEntityIdException, UnavailableResourceException {
        // Given
        BorrowableItem borrowableItem = mock(BorrowableItem.class);
        when(borrowableItem.isAvailable()).thenReturn(false);
        when(borrowableItemRepository.getById(anyLong())).thenReturn(borrowableItem);
        // When
        borrowableItemService.checkIn(anyLong());
        // Then
        assertThat(borrowableItem.isAvailable(), is(false));
    }

    public class BorrowableItemServiceImplementation extends BorrowableItemService {
        public BorrowableItemServiceImplementation(BorrowableItemRepository borrowableItemRepository) {
            super(borrowableItemRepository);
        }
    }
}