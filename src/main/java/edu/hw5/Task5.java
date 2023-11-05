package edu.hw5;

public class Task5 {
    private Task5() {
    }

    private static final String REGEX = "[А-Я]\\d{3}[А-Я]{2}\\d{3}";

    public static boolean isStringMatchRegex(String s) {
        return s.matches(REGEX);
    }
}
