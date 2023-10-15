package edu.project1;

import java.util.Scanner;

public class Game {
    void start(){
        Scanner scanner = new Scanner(System.in);
        GameLogic gl = new GameLogic();
        gl.gameStart();
        while (gl.getAttempts()<= gl.getMaxAttempts()){

            gl.getGuess(scanner.next());
        }
    }
}
