package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private static final Logger LOGGER = LogManager.getLogger();
    private static int attempts = 0;
    private final int maxAttempts = 5;
    private String[] word;
    private String[] userAnswer;

    public int getAttempts() {
        return attempts;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    void gameStart() {
        String word = new Dictionary().getRandom();
        LOGGER.warn(word);
        this.word = word.split("");
        this.userAnswer = "*".repeat(word.length()).split("");
    }

    void getGuess(String s) {
        if (s.equals("*")) {
            defeat();
        } else {
            List<Integer> replaceList = new ArrayList<>();
            for (int i = 0; i < word.length; i++) {
                if (s.equals(word[i])) {
                    replaceList.add(i);
                }
            }
            if (!replaceList.isEmpty()) {
                good(replaceList);
            } else {
                bad();
            }

        }
    }

    void good(List<Integer> list) {
        for (int i : list) {
            this.userAnswer[i] = word[i];
            this.word[i] = "*";
        }
        LOGGER.info("Hit!");
        LOGGER.info(toStr(userAnswer));

    }

    void bad() {
        attempts++;
        LOGGER.info("Missed, mistake " + attempts + " out of 5.");
    }

    public String toStr(String[] array) {
        StringBuilder sb = new StringBuilder();
        for (String i : array) {
            sb.append(i);
        }
        return sb.toString();
    }
    void defeat(){
        LOGGER.error("You LOST!");
        LOGGER.info("Слово: "+toStr(word));
    }
    void win(){
        LOGGER.error("You WIN!");
    }
}
