package edu.hw7.Task4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class MonteKarloMultiStream {
    private final int totalCount;

    public MonteKarloMultiStream(int totalCount) {
        this.totalCount = totalCount;
    }

    //CHECKSTYLE:OFF: checkstyle:MagicNumber
    public double generate() {
        AtomicInteger circleCount = new AtomicInteger(0);

        IntStream.range(0, totalCount)
            .parallel()
            .forEach(i -> {
                double x = Math.random();
                double y = Math.random();

                double distance = Math.pow(x - 0.5, 2) + Math.pow(y - 0.5, 2);
                if (distance <= 0.25) {
                    circleCount.incrementAndGet();
                }
            });

        return 4.0 * ((double) circleCount.get() / totalCount);
    }
}
