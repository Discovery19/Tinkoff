package edu.hw7;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task3Test {
    private Service service;

    @BeforeEach
    void setUp() {
        service = new Service();
        service.add(new Person(1, "John Doe", "123 Main St", "555-1234"));
        service.add(new Person(2, "Jane Smith", "456 Oak St", "555-5678"));
    }

    @Test
    void testAdd() {
        // Arrange
        Person newPerson = new Person(3, "Bob Johnson", "789 Elm St", "555-9876");

        // Act
        service.add(newPerson);

        // Assert
        assertTrue(service.getPeople().contains(newPerson));
    }

    @Test
    void testDelete() {
        // Arrange
        int idToDelete = 1;

        // Act
        service.delete(idToDelete);

        // Assert
        assertThat(service.findByName("John Doe")).isNull();
    }

    @Test
    void testFindByName() {
        // Act
        Person foundPerson = service.findByName("John Doe");

        // Assert
        assertEquals(1, foundPerson.id());
    }

    @Test
    void testFindByAddress() {
        // Act
        Person foundPerson = service.findByAddress("456 Oak St");

        // Assert
        assertEquals(2, foundPerson.id());
    }

    @Test
    void testFindByPhone() {
        // Act
        Person foundPerson = service.findByPhone("555-5678");

        // Assert
        assertEquals(2, foundPerson.id());
    }

}
