package com.workintech.main;

import com.workintech.model.Book;
import com.workintech.model.BookStatus;
import com.workintech.model.Category;
import com.workintech.model.Library;
import com.workintech.payment.CashPayment;
import com.workintech.payment.CreditCardPayment;
import com.workintech.payment.PaymentMethod;
import com.workintech.users.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        Library library = new Library(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        // Kitap ve Yazar oluşturma
        Author author1 = new Author("J.K. Rowling");

        Book book1 = new Book( "Harry Potter", author1, 20.0, BookStatus.AVAILABLE, 2022, Category.Novel);
        library.addBook(book1);



        Author author2 = new Author("George Orwell");
        Book book2 = new Book( "1984", author2, 15.0, BookStatus.AVAILABLE, 2021, Category.Story);
        library.addBook(book2);

        Author author3 = new Author("Franz Kafka");
        Book book3 = new Book("Dönüşüm", author3, 12.0, BookStatus.AVAILABLE, 1915, Category.Story);
        library.addBook(book3);

        Author author4 = new Author("Fyodor Dostoyevski");
        Book book4 = new Book( "Suç ve Ceza", author4, 18.0, BookStatus.AVAILABLE, 1866, Category.Story);
        library.addBook(book4);
        Book book5 = new Book( "Karamazov Kardeşler", author4, 20.0, BookStatus.AVAILABLE, 1880, Category.Story);
        library.addBook(book5);

        System.out.println("Book1 ID: " + book1.getId());
        System.out.println("Book2 ID: " + book2.getId());


        System.out.println("--------------");

        Author person2 = new Author("Mehmet");
        person2.whoYouAre();

        Librarian person3 = new Librarian("Jale", "1234");
        library.addLibrarian(person3);
        person3.whoYouAre();

        System.out.println("--------------1");

        // Yeni bir Member_Record oluşturma
        Person person = new Person("Hasan");
        Address address = new Address("İstanbul", "Beşiktaş", "Barbaros", 15);
        Member_Record memberRecord = new Member_Record(person, address, "5554443322");

        // System.out.println(memberRecord);

        Person person4 = new Person("Ayşe");
        Address address1 = new Address("İstanbul", "Pendik", "Yayalar", 16);
        Member_Record memberRecord1 = new Member_Record(person4, address1, "5549990990");


        Person person5 = new Person("Ayşe");
        Address address2 = new Address("Ankara", "Pendik", "Yayalar", 16);
        Member_Record memberRecord2 = new Member_Record(person4, address1, "5549990990");


        // Member_Record kullanarak yeni bir okuyucu kaydetme
        Reader registeredReader = memberRecord.addAsReader(library);
        Reader registeredReader1 = memberRecord1.addAsReader(library);

        Reader registeredReader2 = memberRecord2.addAsReader(library);

        System.out.println("--------------2");

        // Kaydedilen okuyucuya bir ödeme yöntemi atama
        PaymentMethod creditPayment = new CreditCardPayment();
        registeredReader.setPaymentMethod(creditPayment);
        PaymentMethod cashPayment = new CashPayment();
        registeredReader1.setPaymentMethod(cashPayment);

        // Okuyucunun bir kitap ödünç almasını simüle et
        library.borrowBook("Hasan", "Harry Potter", "Jale", "1234");

        System.out.println("--------------3");

        library.borrowBook("Ayşe", "1984", "Hasan", "2344");

        System.out.println("--------------4");

        library.borrowBook("Hasan", "1984", "Jale", "1234");

        System.out.println("--------------5");

        library.borrowBook("Ayşe", "Dönüşüm", "Jale", "4566");

        System.out.println("--------------6");


        library.generateInvoice(registeredReader, book1);
        library.generateInvoice(registeredReader1, book1);


        System.out.println("--------------7");

        //- Okuyucunun aldığı kitap isimleri
        registeredReader1.printBorrowedBookNames();

        System.out.println("--------------8");
        registeredReader.printBorrowedBookNames();


        System.out.println("--------------9");

        //Okuyucunun aldığı kitap sayıları
        registeredReader1.printNumberOfBorrowedBooks();

        System.out.println("--------------10");
        registeredReader.printNumberOfBorrowedBooks();

        System.out.println("--------------11");

        // Okuyucunun kitabı iade etmesini simüle et
        library.returnBook(registeredReader, book1);

        System.out.println("--------------12");
        List<Book> booksOfOrwell = library.findBooksByAuthor("Fyodor Dostoyevski");
        for (Book book : booksOfOrwell) {
            System.out.println(book.getName());
        }

        System.out.println("--------------13");

        library.generateInvoice(registeredReader, book1);

        System.out.println("--------------14");

        //Listeye kaydedilen okuyucuları getirme
        List<Reader> allReaders = library.getReaders();
        for (Reader reader : allReaders) {
            System.out.println(reader.getName());
        }

        System.out.println("--------------15");
        System.out.println(memberRecord1);

        System.out.println("--------------16");
        System.out.println(library);

        //Okuyucunun aldığı kitap sayıları
        registeredReader1.printNumberOfBorrowedBooks();

        System.out.println("--------------17");
        registeredReader.printNumberOfBorrowedBooks();

        System.out.println("--------------18");

        registeredReader.printRemainingBorrowLimit();
        registeredReader1.printRemainingBorrowLimit();

        System.out.println("--------------19");
        library.printLibrarianNames();

        System.out.println("--------------20");
        library.printAuthorNames();

        System.out.println("--------------21");

    }


}