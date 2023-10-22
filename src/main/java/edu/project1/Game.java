package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game {
    private static final Logger LOGGER = LogManager.getLogger();

    public void start(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        GameLogic gl = new GameLogic();
        gl.gameStart(dictionary.getRandomWord());
        while (gl.getGameStatus()) {
            LOGGER.info("Guess a letter:");
            gl.getGuess(scanner.next());
        }
    }

    public GameLogic startForTest(Dictionary dictionary) {
        GameLogic gameLogic = new GameLogic();
        gameLogic.gameStart(dictionary.getRandomWord());
        return gameLogic;
    }

}
