package com.twu.biblioteca.domain;

public abstract class Entity {

    private Long id = null;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}