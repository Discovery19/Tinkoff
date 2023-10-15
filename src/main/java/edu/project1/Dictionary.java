package edu.project1;

import java.util.Random;

public class Dictionary {
    private static final String[] dictionary={"hello", "world"};
    public String getRandom() {
        int rnd = new Random().nextInt(dictionary.length);
        return dictionary[rnd];
    }
}
