package com.twu.biblioteca.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestableRepositoryTest {

    TestableRepository testableRepository;

    @Before
    public void setUp() {
        this.testableRepository = new TestableRepository();
    }

    @Test
    public void shouldDefineIdsIncrementally() {
        // Given
        TestableEntity testableEntity1 = new TestableEntity();
        TestableEntity testableEntity2 = new TestableEntity();
        // When
        TestableEntity createdTestableEntity1 = testableRepository.create(testableEntity1);
        TestableEntity createdTestableEntity2 = testableRepository.create(testableEntity2);
        // Then
        assertThat(createdTestableEntity1, equalTo(testableEntity1));
        assertThat(createdTestableEntity2, equalTo(testableEntity2));
        assertThat(createdTestableEntity1.getId(), equalTo(1L));
        assertThat(createdTestableEntity2.getId(), equalTo(2L));
    }

    @Test
    public void shouldFindEntityById() {
        // Given
        TestableEntity testableEntity = new TestableEntity();
        // When
        testableRepository.create(testableEntity);
        Optional<TestableEntity> optionalOfFindedEntity = testableRepository.findById(testableEntity.getId());
        // Then
        assertThat(optionalOfFindedEntity.get(), equalTo(testableEntity));
    }

    @Test
    public void shouldReturnAnEmptyOptionalWhenEntityIsNotFound() {
        // Given
        TestableEntity testableEntity = new TestableEntity();
        // When
        testableRepository.create(testableEntity);
        Optional<TestableEntity> OptionalOfTestableEntity = testableRepository.findById(2L);
        // Then
        assertThat(false, is(OptionalOfTestableEntity.isPresent()));
    }

    @Test
    public void shouldDeleteEntityById() {
        // Given
        TestableEntity testableEntity1 = new TestableEntity();
        TestableEntity testableEntity2 = new TestableEntity();
        // When
        testableRepository.create(testableEntity1);
        testableRepository.create(testableEntity2);
        testableRepository.deleteById(1L);
        // Then
        assertThat(testableRepository.getAll().size(), equalTo(1));
        assertThat(testableRepository.getAll().contains(testableEntity1), equalTo(false));
        assertThat(testableRepository.getAll().contains(testableEntity2), equalTo(true));
    }
}
