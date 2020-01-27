package com.twu.biblioteca.domain;

public class Book extends Loanable {

    private String title;

    private String author;

    private Integer publicationYear;

    public Book(String title, String author, Integer publicationYear) {
        super();
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return String.format("%-5s", getId()) +
               String.format("%-40s", title) +
               String.format("%-25s", author) +
               String.format("%-5d", publicationYear);
    }
}