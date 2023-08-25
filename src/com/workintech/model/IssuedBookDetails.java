package com.workintech.model;

import com.workintech.users.Librarian;
import com.workintech.users.Reader;

public class IssuedBookDetails {
    private Reader reader;
    private Librarian librarian;

    public IssuedBookDetails(Reader reader, Librarian librarian) {
        this.reader = reader;
        this.librarian = librarian;
    }

    public Reader getReader() {
        return reader;
    }

    public Librarian getLibrarian() {
        return librarian;
    }
}
