package edu.hw7;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.SynchronizedPersonDatabase;
import edu.hw7.Task3.UsingLockPersonDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Task3Test {
    private SynchronizedPersonDatabase service;

    @BeforeEach
    void setUp() {
        service = new SynchronizedPersonDatabase();
        service.add(new Person(1, "John Doe", "123 Main St", "555-1234"));
        service.add(new Person(2, "Jane Smith", "456 Oak St", "555-5678"));
    }

    @Test
    @DisplayName("add")
    void testAdd() {
        // Arrange
        Person newPerson = new Person(3, "Bob Johnson", "789 Elm St", "555-9876");

        // Act
        service.add(newPerson);

        // Assert
        assertNotNull(service.getPeople().get(newPerson.id()));
    }

    @Test
    @DisplayName("delete")
    void testDelete() {
        // Arrange
        int idToDelete = 1;

        // Act
        service.delete(idToDelete);
        service.delete(idToDelete);

        // Assert
        assertThat(service.findByName("John Doe")).isNull();
    }

    @Test
    @DisplayName("find name")
    void testFindByName() {
        // Act
        Person foundPerson = service.findByName("John Doe");

        // Assert
        assertEquals(1, foundPerson.id());
    }

    @Test
    @DisplayName("find address")
    void testFindByAddress() {
        // Act
        Person foundPerson = service.findByAddress("456 Oak St");

        // Assert
        assertEquals(2, foundPerson.id());
    }

    @Test
    @DisplayName("find phone")
    void testFindByPhone() {
        // Act
        Person foundPerson = service.findByPhone("555-5678");

        // Assert
        assertEquals(2, foundPerson.id());
    }

    @Test
    @DisplayName("Lock")
    void testAddLock() {
        // Arrange
        UsingLockPersonDatabase serviceWithLock = new UsingLockPersonDatabase();
        Person newPerson = new Person(3, "Bob Johnson", "789 Elm St", "555-9876");

        // Act
        serviceWithLock.add(newPerson);

        // Assert
        assertNotNull(serviceWithLock.getPeople().get(newPerson.id()));
    }

    @Test
    @DisplayName("Lock")
    void testRemoveLock() {
        // Arrange
        UsingLockPersonDatabase serviceWithLock = new UsingLockPersonDatabase();
        Person newPerson = new Person(3, "Bob Johnson", "789 Elm St", "555-9876");
        serviceWithLock.add(newPerson);
        // Act
        serviceWithLock.delete(3);

        // Assert
        assertTrue(serviceWithLock.getPeople().isEmpty());
    }
}
