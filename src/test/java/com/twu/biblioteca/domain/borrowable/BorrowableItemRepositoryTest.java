package com.twu.biblioteca.domain.borrowable;

import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BorrowableItemRepositoryTest {

    public BorrowableItemRepository borrowableItemRepository;

    @Mock
    public BorrowableItem entity1;

    @Mock
    public BorrowableItem entity2;

    @Before
    public void setUp() {
        // Given
        borrowableItemRepository = new BorrowableItemRepositoryImplementation();
        borrowableItemRepository.create(entity1);
        borrowableItemRepository.create(entity2);
    }

    @Test
    public void shouldReturnOnlyAvailableEntities() {
        // Given
        when(entity1.isAvailable()).thenReturn(true);
        when(entity2.isAvailable()).thenReturn(false);
        // When
        Set<BorrowableItem> entities = borrowableItemRepository.getAvailables();
        // Then
        assertThat(entities.size(), equalTo(1));
    }

    @Test
    public void shouldReturnOnlyBooksBorrowedByUser() {
        // Given
        User userMock1 = mock(User.class);
        when(userMock1.getId()).thenReturn(1L);

        User userMock2 = mock(User.class);
        when(userMock2.getId()).thenReturn(2L);

        when(entity1.isAvailable()).thenReturn(false);
        when(entity1.getBorrower()).thenReturn(Optional.of(userMock1));

        when(entity2.isAvailable()).thenReturn(false);
        when(entity2.getBorrower()).thenReturn(Optional.of(userMock2));

        // When
        Set<Book> borrowerBooks = borrowableItemRepository.getItemsByBorrowerId(1l);
        // Then
        assertThat(borrowerBooks.contains(entity1), is(true));
        assertThat(borrowerBooks.contains(entity2), is(false));
    }

    public class BorrowableItemRepositoryImplementation extends BorrowableItemRepository<BorrowableItem> {
    }
}
