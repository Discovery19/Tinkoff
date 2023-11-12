package edu.project1;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class GameLogic implements GameExit {
    private static final int MAX_ATTEMPTS = 5;
    private static final int MIN_LEN = 3;

    private int attempts = 0;
    private boolean gameStatus = true;
    private int wordLength;
    private String[] word;
    private String[] userAnswer;

    public boolean getGameStatus() {
        return gameStatus;
    }

    public void gameStart(String str) {
        if (str.length() < MIN_LEN) {
            gameStatus = error();
            return;
        }
        wordLength = str.length();
        log.warn(str);
        word = str.toLowerCase().split("");
        userAnswer = "*".repeat(str.length()).split("");
    }

    public void getGuess(String str) {
        String s = str.toLowerCase();
        if (s.equals("*")) {
            gameStatus = defeat();
        } else {
            wordCheck(s);
        }
        if (attempts == MAX_ATTEMPTS) {
            gameStatus = defeat();
        }
        if (wordLength <= 0) {
            gameStatus = win();
        }

    }

    private void wordCheck(String s) {
        List<Integer> replaceList = new ArrayList<>();
        for (int i = 0; i < word.length; i++) {
            if (s.equals(word[i])) {
                replaceList.add(i);
            }
        }
        if (replaceList.isEmpty()) {
            badAnswer();
        } else {
            goodAnswer(replaceList);
        }
    }

    private void goodAnswer(List<Integer> list) {
        for (int i : list) {
            userAnswer[i] = word[i];
            word[i] = "*";
        }
        wordLength -= list.size();
        log.info("Hit!");
        log.info(buildStringForOutput(userAnswer));
    }

    private void badAnswer() {
        attempts++;
        log.info("Missed, mistake " + attempts + " out of 5.");
        log.info(buildStringForOutput(userAnswer));
    }

    private String buildStringForOutput(String[] array) {
        StringBuilder sb = new StringBuilder();
        for (String i : array) {
            sb.append(i);
        }
        return sb.toString();
    }

}
