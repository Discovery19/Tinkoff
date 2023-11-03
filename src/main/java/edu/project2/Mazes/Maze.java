package edu.project2.Mazes;

import edu.project2.Cell;
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
