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
        Contact[] contacts = Task5.parseContacts(array, mod);
        //assert
        assertThat(contacts).isEqualTo(new Contact[] {new Contact("Thomas", "Aquinas")
            , new Contact("Rene", "Descartes")
            , new Contact("David", "Hume")
            , new Contact("John", "Locke")});
    }

    @Test
    @DisplayName("Обратная сортировка по фамилии, есть имя и фамилия")
    void parseContactsStandardDESC() {
        //arrange
        String[] array = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String mod = "DESC";
        //act
        Contact[] contacts = Task5.parseContacts(array, mod);
        //assert
        assertThat(contacts).isEqualTo(new Contact[] {new Contact("John", "Locke")
            , new Contact("David", "Hume")
            , new Contact("Rene", "Descartes")
            , new Contact("Thomas", "Aquinas")});
    }

    @Test
    @DisplayName("Обратная сортировка по фамилии, есть имя и фамилия, проверка двух букв в фамилии")
    void parseContactsStandardDESCTwo() {
        //arrange
        String[] array = {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        String mod = "DESC";
        //act
        Contact[] contacts = Task5.parseContacts(array, mod);
        //assert
        assertThat(contacts).isEqualTo(new Contact[] {new Contact("Carl", "Gauss")
            , new Contact("Leonhard", "Euler")
            , new Contact("Paul", "Erdos")});
    }

    @Test
    @DisplayName("Обратная сортировка по фамилии, есть имя и нет фамилии, проверка двух букв в фамилии")
    void parseContactsStandardNoSurname() {
        //arrange
        String[] array = {"A", "Leonhard Euler", "Carl Gauss"};
        String mod = "DESC";
        //act
        Contact[] contacts = Task5.parseContacts(array, mod);
        //assert
        assertThat(contacts).isEqualTo(new Contact[] {new Contact("Carl", "Gauss")
            , new Contact("Leonhard", "Euler")
            , new Contact("", "A")});
    }

    @Test
    @DisplayName("пустой")
    void parseContactsEmpty() {
        //arrange
        String[] array = {};
        String mod = "DESC";
        //act
        Contact[] contacts = Task5.parseContacts(array, mod);
        //assert
        assertThat(contacts).isEqualTo(new Contact[] {});
    }

    @Test
    @DisplayName("null")
    void parseContactsNull() {
        //arrange
        String[] array = {null};
        String mod = "DESC";
        //act
        Contact[] contacts = Task5.parseContacts(array, mod);
        //assert
        assertThat(contacts).isEqualTo(new Contact[] {});
    }
}
