package com.twu.biblioteca.application;

public class ApplicationIO {

    private static ApplicationIO instance;

    public static ApplicationIO getInstance() {
        if(instance == null)
            instance = new ApplicationIO();
        return instance;
    }

    public void print(Object object) {
        System.out.println(object);
    }
}
