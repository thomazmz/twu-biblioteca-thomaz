package com.twu.biblioteca.application;

import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.BookRepository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

public class Application {

    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";

    private static Boolean isAlive = true;
    private ApplicationIO applicationIO;
    private ApplicationController applicationController;
    private Menu menu;

    public Application() {

        BookRepository bookRepository = new BookRepository(new LinkedList<Book>(Arrays.asList(
                new Book("Refactoring", "Martin Fowller", 1999),
                new Book("Effective Java", "Joshua Bloch", 2001),
                new Book("Extreme Programming", "Kent Beck", 1999),
                new Book("The Pragmatic Programmer", "Andrew Hunt", 1999),
                new Book("Practices of an Agile Developer", "Venkat Subramaniam", 2006),
                new Book("Clean Code", "Robbert C. Martin", 2008),
                new Book("Test Driven Development By Example", "Kent Beck", 2000),
                new Book("Database Management Systems", "Aldous Huxley", 1996),
                new Book("Practical Unit Testing", "Tomek Kaczanowski", 2019),
                new Book("Building Microservices", "Sam Newman", 2014),
                new Book("Designing Event Driven Systems", "Ben Stopford", 2018),
                new Book("Building Evolutionary Architectures1", "Rebecca Parsons", 2017)
        )));

        applicationIO = new ApplicationIO();

        applicationController = new ApplicationController(bookRepository, applicationIO);

        menu = new Menu("Main Menu");
        menu.setOption("1", "Show all books", applicationController::listBooks);
        menu.setOption("2", "Show all available books", applicationController::listAvailableBooks);
        menu.setOption("Q", "Quit application", this::kill);

    }

    public Application(ApplicationIO applicationIO) {
        this.applicationIO = applicationIO;
    }

    public void start() {
        applicationIO.print(WELCOME_MESSAGE);
        while (isAlive)
            renderMenu();
    }

    private void renderMenu() {
        applicationIO.print(menu);
        readMenuInput();
    }

    private void readMenuInput() {
        Optional<Menu.Option> selectedOption = menu.select(applicationIO.readString());
        if(selectedOption.isPresent()) {
            selectedOption.get().execute();
        } else {
            applicationIO.print("Please, select a valid option.\n");
            readMenuInput();
        }
    }

    public void kill() {
        isAlive = !isAlive;
    }
}