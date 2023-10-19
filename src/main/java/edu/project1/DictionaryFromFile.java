package edu.project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class DictionaryFromFile implements Dictionary {
    String[] dictionary;

    public Dictionary setDictionary(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader br = new BufferedReader(fileReader);
        String line = br.readLine();
        StringBuilder sb = new StringBuilder();
        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }
        dictionary = sb.toString().split(" ");
        br.close();

        return this;
    }

    @Override
    public String getRandomWord() {
        int rnd = new Random().nextInt(dictionary.length);
        return dictionary[rnd];
    }
}
