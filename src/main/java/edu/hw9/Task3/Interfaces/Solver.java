package edu.hw9.Task3.Interfaces;

import edu.hw9.Task3.Cell;
import edu.hw9.Task3.Mazes.Maze;

import java.util.List;
import java.util.Set;

public interface Solver {
    List<Cell> solve(Maze maze);

}
