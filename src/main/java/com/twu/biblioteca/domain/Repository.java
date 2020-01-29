package com.twu.biblioteca.domain;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public abstract class Repository<T extends Entity> {

    private Long counter;

    private Map<Long, T> instances = new LinkedHashMap<>();

    public Repository() {
        counter = 0L;
    }

    public Set<T> getAll() {
        return new LinkedHashSet<>(instances.values());
    }

    public T getById(Long id) throws UnregisteredEntityIdException {
        if(!instances.containsKey(id))
            throw new UnregisteredEntityIdException();
        return instances.get(id);
    }

    public T create(T instance) {
        instance.setId(++counter);
        instances.put(counter, instance);
        return instance;
    }

    public void delete(Long instanceId) {
        if(instances.get(instanceId) != null)
            instances.remove(instanceId);
    }
}