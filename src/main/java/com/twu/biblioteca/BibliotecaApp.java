package com.twu.biblioteca;

public class BibliotecaApp {

    private static String welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";

    private static BibliotecaApp instance;

    private BibliotecaApp() {
    }

    public static BibliotecaApp getInstance() {
        if(instance == null){
            instance = new BibliotecaApp();
        }
        return instance;
    }

    public void start() {
        welcomeUser();
    }

    private void welcomeUser() {
        System.out.print(welcomeMessage);
    }

}
