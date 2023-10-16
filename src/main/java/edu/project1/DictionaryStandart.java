package edu.project1;

import java.util.Random;

public class DictionaryStandart implements Dictionary {
    private static String[] dictionary;

    @Override
    public String getRandomWord() {
        int rnd = new Random().nextInt(dictionary.length);
        return dictionary[rnd];
    }

    @Override
    public Dictionary setDictionary(String dict) {
        dictionary = dict.split(" ");
        return this;
    }
}
