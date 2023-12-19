package edu.hw8.Task3;
//CHECKSTYLE:OFF: checkstyle:ImportOrder

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import org.apache.commons.codec.digest.DigestUtils;

public class Task3 {

    private final int numThreads;
    private final Map<String, String> passwordDataBase;

    //здесь и будет мапа хэш + имя
    private final Map<String, String> mdDatabase = new ConcurrentHashMap<>();
    @Getter private final Map<String, String> result = new HashMap<>();

    public Task3(int numThreads, Map<String, String> map) {
        this.passwordDataBase = map;
        this.numThreads = numThreads;
    }

    private void generatingMD5Passwords() {
        for (Map.Entry<String, String> entry : passwordDataBase.entrySet()) {
            mdDatabase.put(DigestUtils.md5Hex(entry.getValue()), entry.getKey());
        }
    }

    public void nextPassword() {
        while (!mdDatabase.isEmpty()) {
            String pass = generate();
            String md5Pass = DigestUtils.md5Hex(pass);
            if (mdDatabase.containsKey(md5Pass)) {
                result.put(mdDatabase.get(md5Pass), pass);
                mdDatabase.remove(md5Pass);
            }
        }
    }

    //CHECKSTYLE:OFF: checkstyle:MagicNumber
    private String generate() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int minLength = 3;
        int maxLength = 6;
        int passwordLength = random.nextInt(minLength, maxLength);

        StringBuilder password = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        return password.toString();
    }

    public void multiThreadNextPassword() {
        generatingMD5Passwords();
        try (ExecutorService executorService = Executors.newFixedThreadPool(numThreads)) {
            for (int i = 0; i < 10; i++) {
                executorService.submit(this::nextPassword);
            }
            executorService.shutdown();
        }
    }
}
