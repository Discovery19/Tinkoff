package edu.hw8.Task2;
//CHECKSTYLE:OFF: checkstyle:ImportOrder
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class Task2 {
    ReadWriteLock lock = new ReentrantReadWriteLock();

    public int[] parallelFibonacci(int[] indexes) {
        int[] result = new int[indexes.length];
        //CHECKSTYLE:OFF: checkstyle:MagicNumber
        try (MyThreadPool threadPool = new MyFixedThreadPool(4)) {
            threadPool.start();
            CountDownLatch latch = new CountDownLatch(indexes.length);
            AtomicInteger index = new AtomicInteger();
            for (int i : indexes) {
                threadPool.execute(() -> {
                    int res = calculateFibonacci(i);
                    try {
                        lock.writeLock().lock();
                        result[index.get()] = res;
                        index.getAndIncrement();
                        latch.countDown();
                    } finally {
                        lock.writeLock().unlock();
                    }

                });
            }
            latch.await();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public int calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
        }
    }
}
