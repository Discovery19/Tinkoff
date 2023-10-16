package edu.project1;

import java.io.IOException;

public interface Dictionary {
    String getRandomWord();
    Dictionary setDictionary(String dict) throws IOException;
}


