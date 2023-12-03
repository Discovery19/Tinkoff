package edu.hw9.Task3.Mazes;


import edu.hw9.Task3.Cell;
import lombok.Data;

@Data
public class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
    }
}
