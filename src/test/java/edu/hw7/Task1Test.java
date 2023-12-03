package edu.hw7;

import edu.hw7.Task1.Counter;
import edu.hw7.Task1.Task1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Task1Test {

    @Test
    @DisplayName("Три потока 200")
    void testThreeThreads() {
        //arrange
        Task1 task1 = new Task1();
        Counter counter = new Counter();
        //act
        task1.incrementMultiThread(3, counter, 200);
        //assert
        assertThat(counter.getValue()).isEqualTo(200);
    }
    @Test
    @DisplayName("Три потока 201")
    void testThreeThreadsNext() {
        //arrange
        Task1 task1 = new Task1();
        Counter counter = new Counter();
        //act
        task1.incrementMultiThread(3, counter, 201);
        //assert
        assertThat(counter.getValue()).isEqualTo(201);
    }
    @Test
    @DisplayName("Три потока 202")
    void testThreeThreadsNextNext() {
        //arrange
        Task1 task1 = new Task1();
        Counter counter = new Counter();
        //act
        task1.incrementMultiThread(3, counter, 202);
        //assert
        assertThat(counter.getValue()).isEqualTo(202);
    }
    @Test
    @DisplayName("Два потока")
    void testTwoThreads() {
        //arrange
        Task1 task1 = new Task1();
        Counter counter = new Counter();
        //act
        task1.incrementMultiThread(2, counter, 6);
        //assert
        assertThat(counter.getValue()).isEqualTo(6);
    }
}
