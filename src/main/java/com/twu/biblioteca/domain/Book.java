package com.twu.biblioteca.domain;

import com.twu.biblioteca.domain.Entity;

public class Book extends Entity {

    private String title;
    private String author;
    private Integer publicationYear;
    private Boolean available;

    public Book(String title, String author, Integer publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.available = true;
    }

    public Boolean isAvailableForCheckOut() {
        return available;
    }

    public void checkOut() {
        available = false;
    }

    public void checkIn() {
        available = true;
    }

    @Override
    public String toString() {
        return String.format("%-5s", getId()) +
               String.format("%-40s", title) +
               String.format("%-25s", author) +
               String.format("%-5d", publicationYear);
    }
}