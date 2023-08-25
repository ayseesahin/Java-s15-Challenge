package com.workintech.model;

import com.workintech.users.Author;

public class Book {
    private static int counter = 0;
    private int id;
    private String name;
    private Author author;
    private double price;
    private BookStatus status;
    private double publicationYear;
    private Category category;



    public Book(String name, Author author, double price, BookStatus status, double publicationYear, Category category) {
        this.id = ++counter;
        this.name = name;
        this.author = author;
        this.price = price;
        this.status = status;
        this.publicationYear = publicationYear;
        this.category = category;


    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public Author getAuthor() {
        return author;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", price=" + price +
                ", status=" + status +
                ", publicationYear=" + publicationYear +
                ", category=" + category +
                '}';
    }
}
