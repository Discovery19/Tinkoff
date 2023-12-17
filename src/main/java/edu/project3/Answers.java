package edu.project3;

import java.util.HashMap;
import java.util.Map;

public class Answers {
    private static final Map<String, Integer> MAP = new HashMap<>();

    public void add(String key) {
        MAP.compute(key, (w, prev) -> prev != null ? prev + 1 : 1);
    }

    public Map<String, Integer> getMap() {
        return MAP;
    }
}
