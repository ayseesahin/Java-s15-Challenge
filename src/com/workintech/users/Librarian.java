package com.workintech.users;

public class Librarian extends Person {

    private String password;



    public Librarian(String name, String password) {
        super(name);
        this.password = password;
    }

    public boolean validatePassword(String providedPassword) {
        return this.password.equals(providedPassword);
    }

    @Override
    public void whoYouAre() {
        System.out.println(getName() + " is a librarian.");
    }
}
