package edu.hw7.Task4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MonteKarloMultiStream {
    private final int totalCount;
    private AtomicInteger circleCount = new AtomicInteger(0);

    public MonteKarloMultiStream(int totalCount) {
        this.totalCount = totalCount;
    }

    //CHECKSTYLE:OFF: checkstyle:MagicNumber
    public double generate() {

        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < totalCount; i++) {
            service.execute(this::newPoint);
        }

        return 4.0 * ((double) circleCount.get() / totalCount);
    }

    private void newPoint() {
        double x = ThreadLocalRandom.current().nextDouble();
        double y = ThreadLocalRandom.current().nextDouble();

        double distance = Math.pow(x - 0.5, 2) + Math.pow(y - 0.5, 2);
        if (distance <= 0.25) {
            circleCount.incrementAndGet();
        }
    }
}
