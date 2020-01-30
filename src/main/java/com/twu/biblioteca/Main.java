package com.twu.biblioteca;

import com.twu.biblioteca.application.Application;
import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.movie.Movie;

import java.time.Year;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

    private static final List<Movie> movies = new LinkedList<>(Arrays.asList(
        new Movie("The Seven Samurai", "Akira Kurosawa",Year.of(1955), 10),
        new Movie("Reservoir Dogs", "Quentin Tarantino", Year.of(1992), 10),
        new Movie("Pan's Labyrinth", "Guillermo del Toro", Year.of(2006), 10),
        new Movie("The Deer Hunter", "Michael Cimino", Year.of(1978), 10),
        new Movie("Rocky", "John G. Avildsen", Year.of(1976), 10),
        new Movie("Memento", "Christopher Nolan", Year.of(2000), 10),
        new Movie("Die Hard", "John McTiernan", Year.of(1988), 10),
        new Movie("Ghostbusters", "Ivan Reitman", Year.of(1984), 10)
    ));

    private static final List<Book> books = new LinkedList<>(Arrays.asList(
        new Book("Refactoring", "Martin Fowller", Year.of(1999)),
        new Book("Effective Java", "Joshua Bloch", Year.of(2001)),
        new Book("Extreme Programming", "Kent Beck", Year.of(1999)),
        new Book("The Pragmatic Programmer", "Andrew Hunt", Year.of(1999)),
        new Book("Practices of an Agile Developer", "Venkat Subramaniam", Year.of(2006)),
        new Book("Clean Code", "Robbert C. Martin", Year.of(2008)),
        new Book("Test Driven Development By Example", "Kent Beck", Year.of(2000)),
        new Book("Database Management Systems", "Raghu Ramakrishnan", Year.of(1996)),
        new Book("Practical Unit Testing", "Tomek Kaczanowski", Year.of(2019)),
        new Book("Building Microservices", "Sam Newman", Year.of(2014)),
        new Book("Designing Event Driven Systems", "Ben Stopford", Year.of(2018)),
        new Book("Building Evolutionary Architectures", "Rebecca Parsons", Year.of(2017))
    ));

    public static void main(String[] args) {
        Application application = new Application(books, movies);
        application.start();
    }
}
