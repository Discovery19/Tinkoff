package edu.hw7;

import lombok.Getter;
import java.util.concurrent.atomic.AtomicInteger;

@Getter public class Task1 implements Runnable {
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void run() {
        counter.getAndIncrement();
    }

}
