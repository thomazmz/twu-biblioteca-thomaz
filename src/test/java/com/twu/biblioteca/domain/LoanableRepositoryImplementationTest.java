package com.twu.biblioteca.domain;

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
    public void set_up() {
        // Given
        loanableRepository = new LoanableRepositoryImplementation();
        entity1 = new LoanableImplementation();
        entity2 = new LoanableImplementation();
        loanableRepository.create(entity1);
        loanableRepository.create(entity2);
    }

    @Test
    public void should_return_only_available_entities() {
        // When
        entity1.checkOut();
        Set<Loanable> entities = loanableRepository.getAvailables();
        // Then
        assertThat(entities.size(), equalTo(1));
        assertThat(entities.contains(entity1), is(false));
        assertThat(entities.contains(entity2), is(true));
    }
}
