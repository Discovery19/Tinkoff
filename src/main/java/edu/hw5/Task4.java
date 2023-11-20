package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {
    private Task4() {
    }

    private static final String REGEX = "[~!@#$%^&*|]";

    public static boolean isStringMatchRegex(String s) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

}
