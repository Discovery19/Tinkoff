package edu.project1;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameLogic {
    private static final Logger LOGGER = LogManager.getLogger();
    private int attempts = 0;
    private final int maxAttempts = 5;
    private int wordLength;
    private String[] word;
    private String[] userAnswer;
    private boolean gameStatus = true;
    private static final int MIN_LEN = 3;

    public boolean getGameStatus() {
        return gameStatus;
    }

    public void gameStart(String str) {
        if (str.length() < MIN_LEN) {
            error();
            return;
        }
        wordLength = str.length();
        LOGGER.warn(str);
        word = str.toLowerCase().split("");
        userAnswer = "*".repeat(str.length()).split("");
    }

    public void getGuess(String str) {
        String s = str.toLowerCase();
        if (s.equals("*")) {
            defeat();
        } else {
            wordCheck(s);
        }
        if (attempts == maxAttempts) {
            defeat();
        }
        if (wordLength <= 0) {
            win();
        }

    }

    private void wordCheck(String s) {
        List<Integer> replaceList = new ArrayList<>();
        for (int i = 0; i < word.length; i++) {
            if (s.equals(word[i])) {
                replaceList.add(i);
            }
        }
        if (!replaceList.isEmpty()) {
            goodAnswer(replaceList);
        } else {
            badAnswer();
        }
    }

    private void goodAnswer(List<Integer> list) {
        for (int i : list) {
            userAnswer[i] = word[i];
            word[i] = "*";
        }
        wordLength -= list.size();
        LOGGER.info("Hit!");
        LOGGER.info(toStr(userAnswer));
    }

    private void badAnswer() {
        attempts++;
        LOGGER.info("Missed, mistake " + attempts + " out of 5.");
        LOGGER.info(toStr(userAnswer));
    }

    private String toStr(String[] array) {
        StringBuilder sb = new StringBuilder();
        for (String i : array) {
            sb.append(i);
        }
        return sb.toString();
    }

    private void defeat() {
        gameStatus = false;
        LOGGER.error("You LOST!");
    }

    private void win() {
        gameStatus = false;
        LOGGER.error("You WIN!");
    }

    private void error() {
        gameStatus = false;
        LOGGER.error("Неправильно задано слово");
    }
}
