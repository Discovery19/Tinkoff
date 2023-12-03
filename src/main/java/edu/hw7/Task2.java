package edu.hw7;

import java.util.stream.IntStream;

public class Task2 {
    public int factorial(int value) {
        if (value == 0) {
            return 0;
        }
        return IntStream.rangeClosed(1, value).parallel().reduce(1, (a, b) -> a * b);
    }
}
