package com.workintech.model;

import com.workintech.users.*;

import java.util.*;

public class Library {
    private List<Book> books;
    private List<Reader> readers;
    private List<Librarian> librarians;
    private Map<Book, IssuedBookDetails> issuedBooks;


    public Library(List<Book> books, List<Reader> readers, List<Librarian> librarians, Map<Book, IssuedBookDetails> issuedBooks) {
        this.books = books;
        this.readers = readers;
        this.librarians = librarians;
        this.issuedBooks = issuedBooks;
    }
    

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean readerExists(String name) {
        for (Reader r : readers) {
            if (r.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public void addReader(Reader newReader) {
        String readerName = newReader.getName();
        if (!readerExists(readerName)) {
            readers.add(newReader);
            System.out.println(readerName + " has been successfully added.");
        } else {
            System.out.println(readerName + " already added as a reader!");
        }
    }


    public Reader getReaderByName(String name) {
        for (Reader r : readers) {
            if (r.getName().equals(name)) {
                return r;
            }
        }
        return null;
    }
    public Book getBookByName(String bookName) {
        for (Book book : books) {
            if (book.getName().equals(bookName)) {
                return book;
            }
        }
        return null;
    }


    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
    }

    public List<Librarian> getLibrarians() {
        return librarians;
    }

    public void borrowBook(String readerName, String bookName, String librarianName, String librarianPassword) {
        Reader reader = getReaderByName(readerName);
        Book book = getBookByName(bookName);
        Librarian librarian = getLibrarianByName(librarianName);

        if (reader == null) {
            System.out.println("Reader not found.");
            return;
        }

        if (librarian == null) {
            System.out.println("Librarian not found.");
            return;
        }

        if (!librarian.validatePassword(librarianPassword)) {
            System.out.println("Librarian password is incorrect.");
            return;
        }

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (book.getStatus() == BookStatus.AVAILABLE && !issuedBooks.containsKey(book)) {
            book.setStatus(BookStatus.BORROWED);
            IssuedBookDetails details = new IssuedBookDetails(reader, librarian);
            issuedBooks.put(book, details);
            double bookPrice = book.getPrice();
            reader.getPaymentMethod().processPayment(bookPrice);  // Ödeme işlemini yap
            reader.addBorrowedBook(book);

            reader.getMemberRecord().setBooksIssued(true);
            System.out.println(reader.getName() + " has borrowed the book " + book.getName() + " from librarian " + librarian.getName() + ". " + bookPrice + " units have been deducted as borrow fee.");
        } else {
            System.out.println("This book is not available.");
        }
    }

    public Librarian getLibrarianByName(String name) {
        for (Librarian librarian : librarians) {
            if (librarian.getName().equals(name)) {
                return librarian;
            }
        }
        return null;
    }

    public void printLibrarianNames() {
        for (Librarian librarian : librarians) {
            System.out.println(librarian.getName());
        }
    }

    public void returnBook(Reader reader, Book book) {
        if(issuedBooks.get(book).equals(reader)) {
            book.setStatus(BookStatus.AVAILABLE);
            issuedBooks.remove(book);
            double bookPrice = book.getPrice();
            reader.getPaymentMethod().processPayment(-bookPrice);  // İade işlemi için ödeme yöntemini kullan
            reader.removeBorrowedBook(book);
            System.out.println(reader.getName() + " has returned the book " + book.getName() + ". " + bookPrice + " units have been refunded.");
        } else {
            System.out.println("The book was taken by another reader.");
        }
    }


    //belirli bir Reader'ın (okuyucunun) belirli bir Book'u (kitabı) ödünç alıp almadığını kontrol etmek ve eğer ödünç almışsa bu okuyucu için bir fatura oluşturmaktır.
    public void generateInvoice(Reader reader, Book book) {
        if (issuedBooks.containsKey(book) && issuedBooks.get(book).equals(reader)) {
            System.out.println("Invoice for " + reader.getName() + " for borrowing the book " + book.getName());
        } else {
            System.out.println("No invoice generated." + book.getName() + " was not borrowed by " + reader.getName());
        }
    }

    public Set<Author> getAllAuthors() {
        Set<Author> authors = new HashSet<>();
        for (Book book : books) {
            authors.add(book.getAuthor());
        }
        return authors;
    }
    public void printAuthorNames() {
        Set<Author> authors = getAllAuthors();
        for (Author author : authors) {
            System.out.println(author.getName());
        }
    }
    public List<Book> findBooksByAuthor(String authorName) {
        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().getName().equals(authorName)) {
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                ", readers=" + readers +
                ", librarians=" + librarians +
                ", issuedBooks=" + issuedBooks +
                '}';
    }
}
