package com.twu.biblioteca.domain.user;

import com.twu.biblioteca.domain.Entity;
import com.twu.biblioteca.domain.book.Book;
import com.twu.biblioteca.domain.borrowable.BorrowableItem;
import javafx.beans.binding.Bindings;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User extends Entity {

    private String libraryNumber;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private Set<Book> books;

    public User(String libraryNumber, String name, String email, String password, String phoneNumber) {
        super();
        this.books = new HashSet<>();
        this.libraryNumber = libraryNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public Boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void checkOutABook(Book book) {
        this.books.add(book);
    }

    public Set<Book> getBooksCheckedOut() {
        return this.books;
    }

    @Override
    public String toString() {
        return String.format("%-5s", name) +
                String.format("%-30s", email) +
                String.format("%-30s", phoneNumber);
    }
}