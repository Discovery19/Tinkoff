package edu.hw3.Task5;

public record Contact(String name, String surname) {
    public static Contact createContact(String[] fullName) {
        if (fullName.length == 1) {
            return new Contact("", fullName[0]);
        } else if (fullName.length == 2) {
            return new Contact(fullName[0], fullName[1]);
        } else {
            return new Contact("", "");
        }
    }
}

