package com.workintech.users;

public class Author extends Person {
    public Author(String name) {
        super(name);
    }

    @Override
    public void whoYouAre() {
        System.out.println(getName() + " is an author.");
    }
}
