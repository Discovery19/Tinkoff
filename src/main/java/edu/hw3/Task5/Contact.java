package edu.hw3.Task5;

public class Contact {
    private final String name;
    private final String surname;

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Contact(String[] fullName) {
        switch (fullName.length) {
            case 1 -> {
                this.name = "";
                this.surname = fullName[0];
            }
            case 2 -> {
                this.name = fullName[0];
                this.surname = fullName[1];
            }
            default -> {
                this.name = "";
                this.surname = "";
            }
        }
    }

    @Override
    public String toString() {
        return !name.isEmpty() ? name + " " + surname : surname;
    }

    public String getSurname() {
        return surname;
    }
}
