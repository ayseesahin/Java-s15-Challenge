package com.workintech.users;

public class Address {
    private String city;
    private String neighbor;
    private String street;
    private int no;

    public Address(String city, String neighbor, String street, int no) {
        this.city = city;
        this.neighbor = neighbor;
        this.street = street;
        this.no = no;
    }


    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", neighbor='" + neighbor + '\'' +
                ", street='" + street + '\'' +
                ", no=" + no +
                '}';
    }


}
