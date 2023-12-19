package edu.hw8.Task2;
//CHECKSTYLE:OFF: checkstyle:ImportOrder

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLongArray;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task2 {
    private final int threads;
    private AtomicLongArray fibNumbers;
    private CountDownLatch latch;

    public Task2(int threads) {
        this.threads = threads;
    }

    public long calculate(int number){
        if (number <= 1) {
            throw new IllegalArgumentException("number must be more than 1");
        }

        fibNumbers = new AtomicLongArray(number);
        fibNumbers.set(0, 1);
        fibNumbers.set(1, 1);
        latch = new CountDownLatch(number - 2);

        MyThreadPool threadPool = MyFixedThreadPool.create(threads);

        for (int i = 2; i < number; i++) {
            threadPool.execute(createFibonacciThread(i));
        }
        threadPool.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            threadPool.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fibNumbers.get(number - 1);
    }

    private Thread createFibonacciThread(int i) {
        return new Thread(() -> {
            while (fibNumbers.get(i - 1) == 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            fibNumbers.set(i, fibNumbers.get(i - 1) + fibNumbers.get(i - 2));
            latch.countDown();
        });
    }
}
