package edu.hw7.Task4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class MonteKarloMultiStream {
    private final double totalCount;
    private static final int THREAD_NUM = 10;
    private final int[] circleCounts;

    public MonteKarloMultiStream(int totalCount) {
        this.totalCount = totalCount;
        this.circleCounts = new int[THREAD_NUM];
    }

    //CHECKSTYLE:OFF: checkstyle:MagicNumber
    public double generate() {
        ExecutorService service = Executors.newFixedThreadPool(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            final int index = i;
            service.execute(() -> newPoint(index));
        }
        service.shutdown();

        try {
            service.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
        } catch (InterruptedException ignored) {
        }

        int totalCircleCount = 0;
        for (int count : circleCounts) {
            totalCircleCount += count;
        }

        return 4.0 * (totalCircleCount / totalCount);
    }

    private void newPoint(int index) {
        int localCircleCount = 0;
        for (int i = 0; i < totalCount / 10; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();
            double distance = Math.pow(x - 0.5, 2) + Math.pow(y - 0.5, 2);
            if (distance <= 0.25) {
                localCircleCount++;
            }
        }

        circleCounts[index] = localCircleCount;
    }
}

