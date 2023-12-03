package edu.hw9.Task3;

import edu.hw9.Task3.Mazes.BackTrackingGenerator;
import edu.hw9.Task3.Mazes.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.Getter;

public class MultiThreadedMazeSolver {
    private final Cell[][] maze;
    private final int rows;
    private final int cols;
    @Getter private boolean pathFound;
    List<Cell> list  = Collections.synchronizedList(new ArrayList<>());
    ExecutorService executorService = Executors.newCachedThreadPool();

    public MultiThreadedMazeSolver(Maze maze) {
        this.maze = maze.getGrid();
        this.rows = maze.getGrid().length;
        this.cols = maze.getGrid()[0].length;
        this.pathFound = false;
    }

    private boolean isValidPath(int row, int col) {
        return 0 <= row
            && row < rows
            && 0 <= col
            && col < cols
            && maze[row][col].type() == Cell.Type.PASSAGE;
    }

    private boolean explore(int row, int col) {
        if (row == rows - 1 && col == cols - 2) {
                list.add(maze[row][col]);
                pathFound = true;
            return true;
        }

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int startRow = row;
        int endRow = row + 1;

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (isValidPath(newRow, newCol)) {
                if (newRow >= startRow && newRow < endRow) {
                    System.out.println("cell");
                    list.add(maze[newRow][newCol]);
                    if (explore(newRow, newCol)) {
                        executorService.shutdownNow();
                        return true;
                    }
                } else {
                    executorService.submit(() -> explore(newRow, newCol));
                }
            }
        }

        return false;
    }

    public void solve() {
        explore(0, 1);
    }

    public static void main(String[] args) {
        var generator = new BackTrackingGenerator();
        var maze1 = generator.generate(11, 11);
        Render render = new Render();
        MultiThreadedMazeSolver mazeSolver = new MultiThreadedMazeSolver(maze1);
        mazeSolver.executorService.submit(() -> mazeSolver.explore(0,0));
        mazeSolver.executorService.shutdown();
        try {
            mazeSolver.executorService.awaitTermination(10, TimeUnit.SECONDS);
            mazeSolver.executorService.close();
            System.out.println("Completed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n");
        render.render(maze1, mazeSolver.list);

    }
}
