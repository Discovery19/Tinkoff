package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Task5 {
    private Task5() {
    }

    public static Contact[] parseContacts(String[] array, String modifier) {
        List<Contact> contacts = new ArrayList<>();
        for (String string : array) {
            if (string != null) {
                contacts.add(Contact.createContact(string.split(" ")));
            }
        }
        switch (modifier) {
            case "ASC" -> contacts.sort(new NameComparator());
            case "DESC" -> contacts.sort(new NameComparator().reversed());
            default -> {
            }
        }
        return contacts.toArray(new Contact[0]);
    }

    static class NameComparator implements Comparator<Contact> {

        @Override
        public int compare(Contact contact1, Contact contact2) {
            return contact1.surname().compareTo(contact2.surname());
        }
    }

}