package com.twu.biblioteca.domain.book;

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

    public Boolean isAvailable() {
        return this.available;
    }

    public void checkOut() {
        this.available = false;
    }

    public void checkIn() {
        this.available = true;
    }

    @Override
    public String toString() {
        return this.getId() + "| " + this.title + " | " + this.author + " | " + this.publicationYear + "\n";
    }
}