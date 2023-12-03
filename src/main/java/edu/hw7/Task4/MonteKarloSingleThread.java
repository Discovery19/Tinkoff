package edu.hw7.Task4;

import java.util.Random;

public class MonteKarloSingleThread {
    private final int totalCount;
    private int circleCount = 0;
    private final Random random = new Random();

    public MonteKarloSingleThread(int totalCount) {
        this.totalCount = totalCount;
    }

    //CHECKSTYLE:OFF: checkstyle:MagicNumber
    public double generate() {
        for (int i = 0; i < totalCount; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();

            double distance = Math.pow(x - 0.5, 2) + Math.pow(y - 0.5, 2);
            if (distance <= 0.25) {
                circleCount++;
            }
        }

        return 4.0 * ((double) circleCount / totalCount);
    }

}
