package edu.hw10;

import edu.hw10.Task2.CacheProxy;
import edu.hw10.Task2.FibCalculator;
import edu.hw10.Task2.FibonacciCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task2Test {
    @Test
    void testEquals() {
        //arrange
        FibCalculator fibCalculator = new FibonacciCalculator();
        FibCalculator proxy = CacheProxy.create(fibCalculator, FibCalculator.class, "cacheDir");
        //act
        long result1 = proxy.fib(5);
        long result2 = proxy.fib(5);
        //assert
        assertEquals(result1, result2);
    }

    @Test
    void testNumbers() {
        //arrange
        FibCalculator fibCalculator = new FibonacciCalculator();
        FibCalculator proxy = CacheProxy.create(fibCalculator, FibCalculator.class, "cacheDir");
        //act
        long result1 = proxy.fib(10);
        //assert
        assertEquals(55, result1);

    }

}
