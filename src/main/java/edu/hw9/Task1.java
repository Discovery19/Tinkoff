package edu.hw9;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task1 {
    private final Map<String, ConcurrentLinkedQueue<Double>> dataMap = new HashMap<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Map<String, Double> sumMap = new HashMap<>();
    private final Map<String, Double> averageMap = new HashMap<>();
    private final Map<String, Double> maxMap = new HashMap<>();
    private final Map<String, Double> minMap = new HashMap<>();

    public void push(String metricName, double[] data) {
        dataMap.put(metricName, new ConcurrentLinkedQueue<>());
        for (double value : data) {
            dataMap.get(metricName).add(value);
            executorService.submit(() -> updateStats(metricName, value));
        }
    }

    private void updateStats(String metricName, double value) {
        readWriteLock.writeLock().lock();
        try {
            sumMap.merge(metricName, value, Double::sum);
            averageMap.put(metricName, sumMap.get(metricName) / dataMap.get(metricName).size());
            maxMap.merge(metricName, value, Double::max);
            minMap.merge(metricName, value, Double::min);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public String statistic() {
        try {
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        StringBuilder sb = new StringBuilder();
        for (String key : sumMap.keySet()) {
            sb.append(key)
                .append(" сумма: ").append(BigDecimal.valueOf(sumMap.get(key))
                    .setScale(2, RoundingMode.HALF_UP))
                .append(" среднее: ").append(BigDecimal.valueOf(averageMap.get(key))
                    .setScale(2, RoundingMode.HALF_UP))
                .append(" максимум: ").append(maxMap.get(key))
                .append(" минимум: ").append(minMap.get(key)).append("\n");
        }
        return sb.toString();
    }

//    public static void main(String[] args) {
//        Task1 task1 = new Task1();
//        task1.push("metric_name1", new double[] {0.1, 0.05, 1.4, 5.1, 0.3});
//        task1.push("metric_name2", new double[] {0.1, 10.05, 1.4, 5.1, 0.3});
//        task1.push("metric_name3", new double[] {0.05, 1.4, 5.1, 0.3});
//        System.out.println(task1.statistic());
//    }
}
