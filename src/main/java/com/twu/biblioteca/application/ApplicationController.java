package com.twu.biblioteca.application;

import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.book.BookRepository;
import com.twu.biblioteca.domain.movie.Movie;
import com.twu.biblioteca.domain.movie.MovieRepository;

import java.util.Optional;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;

public class ApplicationController {

    private BookRepository bookRepository;

    private MovieRepository movieRepository;

    private ApplicationIO applicationIO;

    public ApplicationController(BookRepository bookRepository, MovieRepository movieRepository, ApplicationIO applicationIO) {
        this.bookRepository = bookRepository;
        this.movieRepository = movieRepository;
        this.applicationIO = applicationIO;
    }

    public void books() {
        applicationIO.print(LINE_BREAK + "BOOKS ( id | title | author| year )" + LINE_BREAK);
        bookRepository.getAll()
                .forEach(book -> applicationIO.print(book + LINE_BREAK));
    }

    public void availableBooks() {
        applicationIO.print(LINE_BREAK + "AVAILABLE BOOKS ( id | title | author| year )" + LINE_BREAK);
        bookRepository.getAvailables()
                .forEach(book -> applicationIO.print(book + LINE_BREAK));
    }

    public void unavailableBooks() {
        applicationIO.print(LINE_BREAK + "UNAVAILABLE BOOKS ( id | title | author| year )" + LINE_BREAK);
        bookRepository.getUnavailables()
                .forEach(book -> applicationIO.print(book + LINE_BREAK));
    }

    public void bookCheckout() {
        applicationIO.print(LINE_BREAK + "Type the ID of the book you would like to check out: ");
        Optional<Long> userInputOptional = applicationIO.readLong();
        if(!userInputOptional.isPresent()) {
            applicationIO.print("Invalid book ID." + LINE_BREAK);
        } else {
            Optional<Book> bookOptional = bookRepository.getById(userInputOptional.get());
            if(!bookOptional.isPresent()) {
                applicationIO.print("Could not found a book with the given ID." + LINE_BREAK);
            } else {
                Book book = bookOptional.get();
                if(!book.isAvailable()) {
                    applicationIO.print("Sorry, that book is not available." + LINE_BREAK);
                } else {
                    book.checkOut();
                    applicationIO.print("Thank you! Enjoy the book" + LINE_BREAK);
                }
            }
        }
    }

    public void bookReturn() {
        applicationIO.print(LINE_BREAK + "Type the ID of the book you would like to return: ");
        Optional<Long> userInputOptional = applicationIO.readLong();
        if(!userInputOptional.isPresent()) {
            applicationIO.print("Invalid book ID." + LINE_BREAK);
        } else {
            Optional<Book> bookOptional = bookRepository.getById(userInputOptional.get());
            if(!bookOptional.isPresent()) {
                applicationIO.print("Could not found a book with the given ID." + LINE_BREAK);
            } else {
                Book book = bookOptional.get();
                if(book.isAvailable()) {
                    applicationIO.print("That is not a valid book to return." + LINE_BREAK);
                } else {
                    book.checkIn();
                    applicationIO.print("Thank you for returning the book." + LINE_BREAK);
                }
            }
        }
    }

    public void availableMovies() {
        applicationIO.print(LINE_BREAK + "AVAILABLE MOVIES ( id | title | director | year | rating )" + LINE_BREAK);
        movieRepository.getAvailables()
                .forEach(book -> applicationIO.print(book + LINE_BREAK));
    }

    public void movieCheckout() {
        applicationIO.print(LINE_BREAK + "Type the ID of the movie you would like to check out: ");
        Optional<Long> userInputOptional = applicationIO.readLong();
        if(!userInputOptional.isPresent()) {
            applicationIO.print("Invalid movie ID." + LINE_BREAK);
        } else {
            Optional<Movie> movieOptional = movieRepository.getById(userInputOptional.get());
            if(!movieOptional.isPresent()) {
                applicationIO.print("Could not found a book with the given ID." + LINE_BREAK);
            } else {
                Movie movie = movieOptional.get();
                if(!movie.isAvailable()) {
                    applicationIO.print("Sorry, that movie is not available." + LINE_BREAK);
                } else {
                    movie.checkOut();
                    applicationIO.print("Thank you! Enjoy the movie" + LINE_BREAK);
                }
            }
        }
    }
}
