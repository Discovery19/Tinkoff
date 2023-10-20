package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Task5 {
    private Task5() {
    }

    public static String[] parseContacts(String[] array, String modifier) {
        List<Contact> contacts = new ArrayList<>();
        for (String string : array) {
            if (string != null) {
                contacts.add(new Contact(string.split(" ")));
            }
        }
        switch (modifier) {
            case "ASC" -> contacts.sort(new NameComparator());
            case "DESC" -> contacts.sort(new NameComparator().reversed());
            default -> {
            }
        }
        String[] result = new String[contacts.size()];
        for (int i = 0; i < contacts.size(); i++) {
            result[i] = contacts.get(i).toString();
        }
        return result;
    }

    static class NameComparator implements Comparator<Contact> {

        @Override
        public int compare(Contact contact1, Contact contact2) {
            return contact1.getSurname().compareTo(contact2.getSurname());
        }
    }

}
