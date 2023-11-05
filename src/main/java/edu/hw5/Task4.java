package edu.hw5;

public class Task4 {
    private Task4() {
    }

    private static final String REGEX = "\\w*[~!@#$%^&*|]\\w*";

    public static boolean isStringMatchRegex(String s) {
        return s.matches(REGEX);
    }

}
