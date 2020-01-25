package com.twu.biblioteca.domain;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Repository<T extends Entity> {

    private Long counter;

    private Map<Long, T> instances = new LinkedHashMap<>();

    public Repository() {
        counter = 0L;
    }

    public Set<T> getAll() {
        return new LinkedHashSet<>(instances.values());
    }

    public Optional<T> getById(Long id) {
        T instance = instances.get(id);
        return Optional.ofNullable(instance);
    }

    public T create(T instance) {
        instance.setId(++counter);
        instances.put(instance.getId(), instance);
        return instance;
    }

    public void delete(Long instanceId) {
        if(instances.get(instanceId) != null)
            instances.remove(instanceId);
    }
}