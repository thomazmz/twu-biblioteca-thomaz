package com.twu.biblioteca.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RepositoryTest {

    RepositoryImplementation testableRepository;

    @Before
    public void set_up() {
        // Given
        this.testableRepository = new RepositoryImplementation();
    }

    @Test
    public void should_define_incremental_id() {
        // When
        EntityImplementation createdEntityImplementation1 = testableRepository.create(new EntityImplementation());
        EntityImplementation createdEntityImplementation2 = testableRepository.create(new EntityImplementation());
        // Then
        assertThat(createdEntityImplementation1.getId(), equalTo(1L));
        assertThat(createdEntityImplementation2.getId(), equalTo(2L));
    }

    @Test
    public void should_get_entity_by_id() {
        // Given
        EntityImplementation entityImplementation = new EntityImplementation();
        testableRepository.create(entityImplementation);
        // When
        Optional<EntityImplementation> optionalOfFindedEntity = testableRepository.getById(entityImplementation.getId());
        // Then
        assertThat(optionalOfFindedEntity.get(), equalTo(entityImplementation));
    }

    @Test
    public void should_delete_entity_by_id() {
        // Given
        EntityImplementation entityImplementation1 = new EntityImplementation();
        EntityImplementation entityImplementation2 = new EntityImplementation();
        testableRepository.create(entityImplementation1);
        testableRepository.create(entityImplementation2);
        // When
        testableRepository.delete(1L);
        // Then
        assertThat(testableRepository.getAll().size(), equalTo(1));
        assertThat(testableRepository.getAll().contains(entityImplementation1), equalTo(false));
        assertThat(testableRepository.getAll().contains(entityImplementation2), equalTo(true));
    }

    @Test
    public void should_return_empty_optional_when_entity_is_not_found() {
        // When
        Optional<EntityImplementation> OptionalOfTestableEntity = testableRepository.getById(1L);
        // Then
        assertThat(false, is(OptionalOfTestableEntity.isPresent()));
    }

    @Test
    public void should_not_raise_exception_when_deleting_by_nonexistent_id() {
        // When
        testableRepository.delete(1L);
        // Then
        // Should not raise any exception
    }
}