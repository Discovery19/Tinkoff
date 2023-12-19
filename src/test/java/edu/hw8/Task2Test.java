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
        Task2 task2 = new Task2(1);
        //act
        long result = task2.calculate(10);
        //assert
        assertThat(result).isEqualTo(55);
    }

    @Test
    @DisplayName("Фибоначчи с многопоточностью")
    void countFibMultiThread() {
        //arrange
        Task2 task2 = new Task2(4);
        int in = 9;
        //act
        var result = task2.calculate(in);
        //assert
        assertThat(result).isEqualTo(34);
    }

    @Test
    @DisplayName("Фибоначчи с многопоточностью много чисел")
    void countFibMultiThreadMore() {
        //arrange
        Task2 task2 = new Task2(4);
        int in = 15;
        //act
        var result = task2.calculate(in);
        //assert
        assertThat(result).isEqualTo(610);
    }
}
