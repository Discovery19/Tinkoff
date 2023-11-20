package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task2Test {

    @Test
    @DisplayName("Факториал числа 5")
    void testFactorialFive() {
        // Arrange
        Task2 task2 = new Task2();

        // Act
        int result = task2.factorial(5);

        // Assert
        assertEquals(120, result);
    }

    @Test
    @DisplayName("Факториал числа 0")
    void testFactorialZero() {
        // Arrange
        Task2 task2 = new Task2();

        // Act
        int result = task2.factorial(0);

        // Assert
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Факториал числа 1")
    void testFactorialOfOne() {
        // Arrange
        Task2 task2 = new Task2();

        // Act
        int result = task2.factorial(1);

        // Assert
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Факториал числа 10")
    void testFactorialOfTen() {
        // Arrange
        Task2 task2 = new Task2();

        // Act
        int result = task2.factorial(10);

        // Assert
        assertEquals(3628800, result);
    }
}
