package edu.hw8;

import edu.hw8.Task2.Task2;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {
    @Test
    @DisplayName("Фибоначчи с одним потоком")
    void countFibOneThread() {
        //arrange
        Task2 task2 = new Task2();
        //act
        int result = task2.calculateFibonacci(10);
        //assert
        assertThat(result).isEqualTo(55);
    }

    @Test
    @DisplayName("Фибоначчи с многопоточностью")
    void countFibMultiThread() {
        //arrange
        Task2 task2 = new Task2();
        int[] in = {9};
        //act
        var result = task2.parallelFibonacci(in);
        //assert
        assertThat(result).isEqualTo(new int[] {34});
    }

    @Test
    @DisplayName("Фибоначчи с многопоточностью много чисел")
    void countFibMultiThreadMore() {
        //arrange
        Task2 task2 = new Task2();
        int[] in = {10, 15, 4, 5, 6, 7, 8, 9};
        //act
        var result = task2.parallelFibonacci(in);
        Arrays.sort(result);
        //assert
        assertThat(result).isEqualTo(new int[] {3, 5, 8, 13, 21, 34, 55, 610});
    }
}
