package edu.project1;

import java.util.Scanner;

public class Game {
    public void start(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        GameLogic gl = new GameLogic();
        GameLogic.gameStart(dictionary.getRandomWord());
        while (gl.getGameStatus()) {
            gl.getGuess(scanner.next());
        }
    }

    public GameLogic startForTest(Dictionary dictionary) {
        GameLogic gl = new GameLogic();
        GameLogic.gameStart(dictionary.getRandomWord());
        return gl;
    }

}
