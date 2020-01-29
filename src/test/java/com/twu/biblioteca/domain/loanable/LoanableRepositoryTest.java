package com.twu.biblioteca.domain.loanable;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoanableRepositoryTest {

    public LoanableRepository loanableRepository;

    @Mock
    public Loanable entity1;

    @Mock
    public Loanable entity2;

    @Before
    public void setUp() {
        // Given
        loanableRepository = new LoanableRepositoryImplementation();
        loanableRepository.create(entity1);
        loanableRepository.create(entity2);
    }

    @Test
    public void shouldReturnOnlyAvailableEntities() {
        // Given
        when(entity1.isAvailable()).thenReturn(true);
        when(entity2.isAvailable()).thenReturn(false);
        // When
        Set<Loanable> entities = loanableRepository.getAvailables();
        // Then
        assertThat(entities.size(), equalTo(1));
    }

    public class LoanableRepositoryImplementation extends LoanableRepository<Loanable> {
    }
}
