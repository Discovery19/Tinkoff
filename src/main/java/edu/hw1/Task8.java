package edu.hw1;

public class Task8 {
    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1 && (moves(i, j, board))) {
                    return false;

                }
            }
        }
        return true;
    }

    private static final int[] MOVE_I = {-1, 1, 2, 2, 1, -1, -2, -2};
    private static final int[] MOVE_J = {-2, -2, -1, 1, 2, 2, 1, -1};

    private static boolean moves(int i, int j, int[][] board) {
        boolean bool = false;
        for (int k = 0; k < MOVE_I.length; k++) {
            if ((i + MOVE_I[k]) >= 0 && (i + MOVE_I[k]) < board.length && (j + MOVE_J[k]) >= 0
                && (j + MOVE_J[k]) < board.length) {
                bool = board[i + MOVE_I[k]][j + MOVE_J[k]] == 1;
            }
            if (bool) {
                return true;
            }
        }
        return false;
    }
}
