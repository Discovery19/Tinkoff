package edu.project2.Interfaces;

import edu.project2.Cell;
import java.util.List;

public interface Renderer {
    String render(Cell[][] grid, List<Cell> path);
    //String render(Maze maze, List<Coordinate> path);
}
