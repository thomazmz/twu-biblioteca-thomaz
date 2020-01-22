package com.twu.biblioteca.domain;

import java.util.HashSet;
import java.util.Set;

public abstract class Repository<T extends Entity> {

    private Long counter;

    private Set<T> instances = new HashSet<>();

    public Repository() {
        counter = 0L;
    }

    public Set<T> getAll() {
        return this.instances;
    };

    public void create(T instance) {
        instance.setId(counter++);
        this.instances.add(instance);
    }
}