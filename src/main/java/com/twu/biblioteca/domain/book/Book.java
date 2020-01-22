package com.twu.biblioteca.domain.book;

import com.twu.biblioteca.domain.Entity;

public class Book extends Entity {

    private String title;

    private String author;

    private Integer publicationYear;

    public Book(String title, String author, Integer publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    @Override
    public String toString() {
        return this.getId() + "| " + this.title + " | " + this.author + " | " + this.publicationYear + "\n";
    }
}
