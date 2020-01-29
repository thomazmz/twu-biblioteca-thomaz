package com.twu.biblioteca.domain.book;

import com.twu.biblioteca.domain.lonnable.Loanable;

import java.time.Year;

public class Book extends Loanable {

    private String title;

    private String author;

    private Year year;

    public Book(String title, String author, Year year) {
        super();
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%-5s", getId()) +
               String.format("%-40s", title) +
               String.format("%-25s", author) +
               String.format("%-5s", year);
    }
}