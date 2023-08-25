package com.workintech.users;

import com.workintech.model.Library;
import com.workintech.payment.PaymentMethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Member_Record {
    private static int nextMemberId = 1;
    private int member_id;

    private String date_of_membership;
    private boolean no_books_issued;
    private int max_book_limit;
    private Person person;
    private Address address;
    private String phone_no;


    public Member_Record(Person person, Address address, String phone_no) {
        this.member_id = getNextMemberId();
        this.date_of_membership = getCurrentDate();
        this.no_books_issued = false;
        this.max_book_limit = 5;
        this.person = person;
        this.address = address;
        this.phone_no = phone_no;
    }

    // Otomatik olarak artan member_id'yi döndüren ve güncelleyen metod
    private static int getNextMemberId() {
        return nextMemberId++;
    }

    // Şu anki tarihi döndüren metod
    private static String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }


    public boolean isBooksIssued(){
        return no_books_issued;
    }

    public void setBooksIssued(boolean value) {
        this.no_books_issued = value;
    }

    public Person getPerson() {
        return person;
    }

    public Reader addAsReader(Library library) {
        if (this.person != null) {
            return Reader.registerReader(this, library);
        } else {
            System.out.println("This member does not have a valid person record.");
            return null;
        }
    }



    @Override
    public String toString() {
        return "Member_Record{" +
                "member_id=" + member_id +
                ", date_of_membership=" + date_of_membership +
                ", no_books_issued=" + no_books_issued +
                ", max_book_limit=" + max_book_limit +
                ", person=" + person +
                ", address=" + address +
                ", phone_no=" + phone_no +
                '}';
    }



}
