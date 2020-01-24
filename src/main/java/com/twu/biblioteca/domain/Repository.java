package com.twu.biblioteca.domain;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Repository<T extends Entity> {

    private Long counter;

    private Map<Long, T> instances = new HashMap();

    public Repository() {
        counter = 0L;
    }

    public Set<T> getAll() {
        return this.instances.values().stream().collect(Collectors.toSet());
    };

    public T create(T instance) {
        instance.setId(++counter);
        this.instances.put(instance.getId(), instance);
        return instance;
    }

    public Optional<T> findById(Long id) {
        T instance = this.instances.get(id);
        return Optional.ofNullable(instance);
    }

    public void deleteById(Long id) {
        T instance = this.instances.get(id);
        this.instances.remove(instance.getId());
    }
}