package edu.hw8.Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyFixedThreadPool implements MyThreadPool {
    private final Thread[] threads;
    private final BlockingQueue<Runnable> taskQueue;

    public static MyFixedThreadPool create(int numThreads) {
        return new MyFixedThreadPool(numThreads);
    }

    public MyFixedThreadPool(int numThreads) {
        this.threads = new Thread[numThreads];
        this.taskQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void start() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Worker());
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        for (Thread thread : threads) {
            if (thread != null) {
                thread.interrupt();
            }
        }
    }

    private class Worker implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
