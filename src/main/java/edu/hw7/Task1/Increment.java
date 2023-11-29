package edu.hw7.Task1;

public class Increment implements Runnable {

    private final Counter counter;
    private final int result;

    public Increment(Counter counter, int result) {
        this.counter = counter;
        this.result = result;
    }

    @Override
    public void run() {
        synchronized (counter) {
            while (counter.getValue() < result) {
                counter.increment();
            }
        }
    }
}
