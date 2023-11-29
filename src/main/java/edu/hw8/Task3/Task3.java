package edu.hw8.Task3;
//CHECKSTYLE:OFF: checkstyle:ImportOrder

import lombok.Getter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task3 {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    @Getter private Map<String, String> passwordDataBase = Map.of(
        "user1", "abcd1",
        "user2", "pass2",
        "user3", "pqwes3",
        "user4", "pavn4",
        "user5", "12s5",
        "user6", "p3346",
        "user7", "paq7",
        "user8", "pass",
        "user9", "pa9s",
        "user10", "ps10as"
    );
    private final Map<Integer, String> mdDatabase = new HashMap<>();
    @Getter private final Map<String, String> result = new HashMap<>();

    private void generatingMD5() {
        for (Map.Entry<String, String> entry : passwordDataBase.entrySet()) {
            mdDatabase.put(entry.getValue().hashCode(), entry.getKey());
        }
    }

    public void nextPassword() {
        while (!mdDatabase.isEmpty()) {
            String pass = generate();
            if (mdDatabase.containsKey(pass.hashCode())) {
                lock.writeLock().lock();
                try {
                    result.put(mdDatabase.get(pass.hashCode()), pass);
                    mdDatabase.remove(pass.hashCode());
                } finally {
                    lock.writeLock().unlock();
                }

            }
        }
    }

    //CHECKSTYLE:OFF: checkstyle:MagicNumber
    private String generate() {
        Random random = new Random();
        int minLength = 4;
        int maxLength = 6;
        int passwordLength = random.nextInt(maxLength - minLength + 1) + minLength;

        StringBuilder password = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        return password.toString();
    }

//    public static void main(String[] args) {
//
//
//
//
//        Task3 task3 = new Task3();
//        task3.generatingMD5();
//        Instant start = Instant.now();
//        task3.multiThreadNextPassword();
//        Instant end = Instant.now();
//        Duration duration = Duration.between(start, end);
//        System.out.println(duration.toMillis());
//        Map<String, String> map = task3.result;
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
//    }

    public void multiThreadNextPassword() {
        generatingMD5();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executorService.submit(this::nextPassword);
        }

        executorService.shutdown();
    }
}
