package com.twu.biblioteca.application;

public abstract class ApplicationInterfaceComponent {

    protected ApplicationInterface applicationInterface;

    public ApplicationInterfaceComponent(ApplicationInterface applicationInterface) {
        this.applicationInterface = applicationInterface;
    }

    public abstract void render();
}