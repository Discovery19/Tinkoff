package edu.hw3;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Task5 {
    private Task5() {
    }

    public static void main(String[] args) {
//        parseContacts(new String[] {"Carl Gauss", "Leonhard Euler", "Paul Erdos"}, "ASC");
//        parseContacts(new String[] {}, "ASC");
        parseContacts(new String[] {"John Locke", "Aquinas", "David Hume", "Rene Descartes"}, "DESC");
    }

    public static Contact[] parseContacts(String[] list, String modifier) {
        Contact[] contacts = new Contact[list.length];
        for (int i = 0; i < list.length; i++) {
            contacts[i] = new Contact(list[i].split(" "));
        }
        switch (modifier) {
            case "ASC" -> Arrays.sort(contacts, new NameComparator());
            case "DESC" -> Arrays.sort(contacts, new NameComparator().reversed());
        }
        Arrays.sort(contacts, new NameComparator());
        for (Contact contact : contacts) {
            System.out.println(contact.getSurname() + " " + contact.getName());
        }
        return contacts;
    }

    static class NameComparator implements Comparator<Contact> {

        @Override
        public int compare(Contact contact1, Contact contact2) {
            return contact1.getSurname().compareTo(contact2.getSurname());
        }
    }

    static class Contact {
        private String name;
        private String surname;

        public Contact(String[] fullName) {
            switch (fullName.length) {
                case 0 -> {
                    this.name = "";
                    this.surname = "";
                }
                case 1 -> {
                    this.name = "";
                    this.surname = fullName[0];
                }
                case 2 -> {
                    this.name = fullName[0];
                    this.surname = fullName[1];
                }
            }
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }
    }
}
