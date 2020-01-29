package com.twu.biblioteca.domain.loannable;

import com.twu.biblioteca.domain.lonnable.Loanable;
import com.twu.biblioteca.domain.lonnable.LoanableRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoanableRepositoryImplementationTest {

    public LoanableRepository loanableRepository;

    public Loanable entity1;
    public Loanable entity2;

    @Before
    public void setUp() {
        // Given
        loanableRepository = new LoanableRepositoryImplementation();
        entity1 = new LoanableImplementation();
        entity2 = new LoanableImplementation();
        loanableRepository.create(entity1);
        loanableRepository.create(entity2);
    }

    @Test
    public void shouldReturnOnlyAvailableEntities() {
        // When
        entity1.checkOut();
        Set<Loanable> entities = loanableRepository.getAvailables();
        // Then
        assertThat(entities.size(), equalTo(1));
        assertThat(entities.contains(entity1), is(false));
        assertThat(entities.contains(entity2), is(true));
    }
}
