package edu.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {
    private Task3() {
    }

    public static <T> String freqDict(List<T> list) {
        HashMap<T, Integer> result = new HashMap<>();
        for (T t : list) {
            if (result.containsKey(t)) {
                result.put(t, result.get(t) + 1);
            } else {
                result.put(t, 1);
            }
        }
        result.entrySet().stream().sorted(Map.Entry.<T, Integer>comparingByValue().reversed());
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<T, Integer> entry : result.entrySet()) {
            sb.append(entry.getKey() + ": " + entry.getValue() + "\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("this", "and", "that", "and", "that", "that");
        System.out.println(freqDict(list));
    }
}
