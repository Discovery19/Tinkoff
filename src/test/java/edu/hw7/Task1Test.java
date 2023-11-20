package edu.hw7;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
@Slf4j
class Task1Test {

    @Test
    @DisplayName("Три потока")
    void testThreeThreads() {
        //arrange
        Task1 task1 = new Task1();
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task1);
        Thread thread3 = new Thread(task1);
        //act
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        //assert
        assertThat(task1.getCounter().get()).isEqualTo(3);
    }

    @Test
    @DisplayName("Два потока")
    void testTwoThreads() {
        //arrange
        Task1 task1 = new Task1();
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task1);
        //act
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        //assert
        assertThat(task1.getCounter().get()).isEqualTo(2);
    }
}
