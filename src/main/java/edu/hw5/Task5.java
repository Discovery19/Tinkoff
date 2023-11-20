package edu.hw5;

public class Task5 {
    private Task5() {
    }

    private static final String REGEX = "[A-ZА-Я]\\d{3}[A-ZА-Я]{2}\\d{2,3}";

    public static boolean isStringMatchRegex(String s) {
        return s.matches(REGEX);
    }
}
