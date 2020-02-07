package com.twu.biblioteca.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class RepositoryTest {

    RepositoryImplementation testableRepository;

    @Before
    public void setUp() {
        // Given
        this.testableRepository = new RepositoryImplementation();
    }

    @Test
    public void shouldDefineIncrementalId() {
        // When
        Entity entity1 = testableRepository.create(new EntityImplementation());
        Entity entity2 = testableRepository.create(new EntityImplementation());
        // Then
        assertThat(entity1.getId(), equalTo(1L));
        assertThat(entity2.getId(), equalTo(2L));
    }

    @Test
    public void shouldGetEntityById() throws UnregisteredEntityIdException {
        // Given
        Entity entity = new EntityImplementation();
        testableRepository.create(entity);
        // When
        Entity findedEntity = testableRepository.getById(entity.getId());
        // Then
        assertThat(findedEntity, equalTo(entity));
    }

    @Test
    public void shouldDeleteEntityById() {
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

    @Test(expected = UnregisteredEntityIdException.class)
    public void shouldThrowExceptionWhenEntityIsNotFound() throws UnregisteredEntityIdException {
        // When
        testableRepository.getById(1L);
        // Then: Should throw exception
    }

    @Test
    public void shouldNotRaiseExceptionWhenDeletingByNonexistentId() {
        // When
        testableRepository.delete(1L);
        // Then: Should not raise any exception
    }

    public class RepositoryImplementation extends Repository<Entity> {
    }

    public class EntityImplementation extends Entity {
    }
}