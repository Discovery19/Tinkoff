package edu.hw8.Task2;

public interface MyThreadPool extends AutoCloseable {
    void start();

    void execute(Runnable runnable);
}
