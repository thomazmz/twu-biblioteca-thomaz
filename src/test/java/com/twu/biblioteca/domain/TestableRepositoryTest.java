package com.twu.biblioteca.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestableRepositoryTest {

    TestableRepository testableRepository;

    @Before
    public void set_up() {
        // Given
        this.testableRepository = new TestableRepository();
    }

    @Test
    public void should_define_incremental_id() {
        // When
        TestableEntity createdTestableEntity1 = testableRepository.create(new TestableEntity());
        TestableEntity createdTestableEntity2 = testableRepository.create(new TestableEntity());
        // Then
        assertThat(createdTestableEntity1.getId(), equalTo(1L));
        assertThat(createdTestableEntity2.getId(), equalTo(2L));
    }

    @Test
    public void should_get_entity_by_id() {
        // Given
        TestableEntity testableEntity = new TestableEntity();
        testableRepository.create(testableEntity);
        // When
        Optional<TestableEntity> optionalOfFindedEntity = testableRepository.getById(testableEntity.getId());
        // Then
        assertThat(optionalOfFindedEntity.get(), equalTo(testableEntity));
    }

    @Test
    public void should_delete_entity_by_id() {
        // Given
        TestableEntity testableEntity1 = new TestableEntity();
        TestableEntity testableEntity2 = new TestableEntity();
        testableRepository.create(testableEntity1);
        testableRepository.create(testableEntity2);
        // When
        testableRepository.delete(1L);
        // Then
        assertThat(testableRepository.getAll().size(), equalTo(1));
        assertThat(testableRepository.getAll().contains(testableEntity1), equalTo(false));
        assertThat(testableRepository.getAll().contains(testableEntity2), equalTo(true));
    }

    @Test
    public void should_return_empty_optional_when_entity_is_not_found() {
        // When
        Optional<TestableEntity> OptionalOfTestableEntity = testableRepository.getById(1L);
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