package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {
    }

    public static List<String> clusterize(String string) {
        int len = 0;
        int open = 0;
        int close = 0;
        List<String> result = new ArrayList<>();
        while (len < string.length()) {
            switch (string.charAt(len)) {
                case '(' -> open++;
                case ')' -> close++;
                default -> throw new IllegalArgumentException("Unexpected value: " + string.charAt(len));
            }
            if (open < close) {
                throw new IllegalStateException("Unbalanced string");
            }
            if (open == close) {
                result.add(string.substring(len - open - close + 1, len + 1));
                open = 0;
                close = 0;
            }
            len++;
        }
        return result;
    }
}
