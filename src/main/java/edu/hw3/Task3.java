package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {
    private Task3() {
    }

    public static <T> String freqDict(List<T> list) {
        Map<T, Integer> result = new HashMap<>();
        for (T t : list) {
            if (result.containsKey(t)) {
                result.put(t, result.get(t) + 1);
            } else {
                result.put(t, 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<T, Integer> entry : result.entrySet()) {
            sb.append(entry.getKey() + ": " + entry.getValue() + "\n");
        }
        return sb.toString();
    }
}
