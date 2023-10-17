package edu.project1;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameLogic {
    private static final Logger LOGGER = LogManager.getLogger();
    private static int attempts = 0;
    private final int maxAttempts = 5;
    private static int wordLength;
    private static String[] word;
    private static String[] userAnswer;
    private static boolean gameStatus = true;

    public boolean getGameStatus() {
        return gameStatus;
    }

    public void gameStart(String str) {
        if (str.isEmpty()) {
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
        LOGGER.info("Guess a letter:");
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

    private static void goodAnswer(List<Integer> list) {
        for (int i : list) {
            userAnswer[i] = word[i];
            word[i] = "*";
        }
        wordLength -= list.size();
        LOGGER.info("Hit!");
        LOGGER.info(toStr(userAnswer));
    }

    private static void badAnswer() {
        attempts++;
        LOGGER.info("Missed, mistake " + attempts + " out of 5.");
        LOGGER.info(toStr(userAnswer));
    }

    private static String toStr(String[] array) {
        StringBuilder sb = new StringBuilder();
        for (String i : array) {
            sb.append(i);
        }
        return sb.toString();
    }

    private static void defeat() {
        gameStatus = false;
        LOGGER.error("You LOST!");
    }

    private static void win() {
        gameStatus = false;
        LOGGER.error("You WIN!");
    }

    private static void error() {
        gameStatus = false;
        LOGGER.error("Неправильно задано слово");
    }
}
