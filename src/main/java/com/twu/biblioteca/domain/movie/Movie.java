package com.twu.biblioteca.domain.movie;

import com.twu.biblioteca.domain.borrowable.BorrowableItem;

import java.time.Year;

public class Movie extends BorrowableItem {

    private String title;

    private String director;

    private Year year;

    private Integer rating;

    public Movie(String title, String director, Year year, Integer rating) {
        super();
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("%-5s", getId()) +
                String.format("%-30s", title) +
                String.format("%-30s", director) +
                String.format("%-8s", year) +
                String.format("%-2s", rating);
    }
}