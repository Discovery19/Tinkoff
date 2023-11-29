package edu.hw7.Task1;

import lombok.Getter;

@Getter public class Task1 {
    public void incrementMultiThread(int threadCount, Counter counter, int result) {
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(new Increment(counter, result));
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
