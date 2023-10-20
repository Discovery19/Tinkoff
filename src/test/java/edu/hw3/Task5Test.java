package edu.hw3;

import edu.hw3.Task5.Contact;
import edu.hw3.Task5.Task5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task5Test {
    @Test
    @DisplayName("Сортировка по фамилии, есть имя и фамилия")
    void parseContactsStandardASC() {
        //arrange
        String[] array = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String mod = "ASC";
        //act
        String[] contacts = Task5.parseContacts(array, mod);
        //assert
        assertThat(contacts).isEqualTo(new String[] {"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"});
    }
    @Test
    @DisplayName("Обратная сортировка по фамилии, есть имя и фамилия")
    void parseContactsStandardDESC() {
        //arrange
        String[] array = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String mod = "DESC";
        //act
        String[] contacts = Task5.parseContacts(array, mod);
        //assert
        assertThat(contacts).isEqualTo(new String[] {"John Locke", "David Hume", "Rene Descartes", "Thomas Aquinas"});
    }
    @Test
    @DisplayName("Обратная сортировка по фамилии, есть имя и фамилия, проверка двух букв в фамилии")
    void parseContactsStandardDESCTwo() {
        //arrange
        String[] array = {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        String mod = "DESC";
        //act
        String[] contacts = Task5.parseContacts(array, mod);
        //assert
        assertThat(contacts).isEqualTo(new String[] {"Carl Gauss", "Leonhard Euler", "Paul Erdos"});
    }
    @Test
    @DisplayName("Обратная сортировка по фамилии, есть имя и нет фамилии, проверка двух букв в фамилии")
    void parseContactsStandardNoSurname() {
        //arrange
        String[] array = {"A", "Leonhard Euler", "Carl Gauss"};
        String mod = "DESC";
        //act
        String[] contacts = Task5.parseContacts(array, mod);
        //assert
        assertThat(contacts).isEqualTo(new String[] {"Carl Gauss", "Leonhard Euler", "A"});
    }
    @Test
    @DisplayName("пустой")
    void parseContactsEmpty() {
        //arrange
        String[] array = {};
        String mod = "DESC";
        //act
        String[] contacts = Task5.parseContacts(array, mod);
        //assert
        assertThat(contacts).isEqualTo(new String[] {});
    }
    @Test
    @DisplayName("null")
    void parseContactsNull() {
        //arrange
        String[] array = {null};
        String mod = "DESC";
        //act
        String[] contacts = Task5.parseContacts(array, mod);
        //assert
        assertThat(contacts).isEqualTo(new String[] {});
    }
}
