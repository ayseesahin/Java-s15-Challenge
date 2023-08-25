package com.workintech.users;

import com.workintech.model.Book;
import com.workintech.model.Library;
import com.workintech.payment.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {
    private static List<Reader> allReaders = new ArrayList<>(); // Tüm okuyucuları kaydetmek için static liste
    private List<Book> borrowedBooks = new ArrayList<>();
    private int borrowedBooksCount = 0;
     private Member_Record memberRecord;
    private PaymentMethod paymentMethod;
    private Library library;



    private Reader(String name){
        super(name);
    }

    public Reader(Member_Record memberRecord, Library library) {
        super(memberRecord.getPerson().getName());
        this.memberRecord = memberRecord;
        this.library = library;
        this.borrowedBooks = new ArrayList<>();  // Listeyi başlatma
    }

    public Reader(String name, List<Book> borrowedBooks, int borrowedBooksCount, Member_Record memberRecord, PaymentMethod paymentMethod, Library library) {
        super(name);
        this.borrowedBooks = borrowedBooks;
        this.borrowedBooksCount = borrowedBooksCount;
        this.memberRecord = memberRecord;
        this.paymentMethod = paymentMethod;
        this.library = library;
    }


    public static Reader registerReader(Member_Record memberRecord, Library library) {
        Reader newReader = new Reader(memberRecord, library);
        library.addReader(newReader);  // Library sınıfındaki addReader metodunu doğrudan burada çağırıyoruz
        allReaders.add(newReader);     // Aynı zamanda global liste olan allReaders'a da ekliyoruz.
        return newReader;              // Kaydedilen okuyucuyu geri döndürüyoruz
    }


    @Override
    public void whoYouAre() {
        System.out.println(getName() + " is a reader.");
    }

    public void borrowBook(Book book) {
        if(borrowedBooksCount < 5) {
            borrowedBooks.add(book);  // Kitabı ödünç alındı olarak işaretle
            borrowedBooksCount++;
        } else {
            System.out.println("Limit reached");
        }
    }

    public void returnBook(Book book) {
        if(borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);  // Kitabı ödünç alınan kitaplar listesinden çıkar
            borrowedBooksCount--;
        }
    }


    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
        borrowedBooksCount++;
    }

    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
        borrowedBooksCount--;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void printBorrowedBookNames() {
        for (Book book : borrowedBooks) {
            System.out.println(book.getName());
        }
    }

    public int getBorrowedBooksCount() {
        return borrowedBooksCount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Member_Record getMemberRecord() {
        return memberRecord;
    }

    public void setMemberRecord(Member_Record memberRecord) {
        this.memberRecord = memberRecord;
    }

    public void printNumberOfBorrowedBooks() {
        System.out.println(getName() + " has borrowed " + borrowedBooksCount + " books.");
    }

    public void printRemainingBorrowLimit() {
        int remainingBooks = 5 - borrowedBooksCount;  // Assuming 5 is the maximum limit
        System.out.println(getName() + " can borrow " + remainingBooks + " more books.");
    }


}
