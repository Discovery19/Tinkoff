package edu.project2.Solves;

import edu.project2.Cell;
import edu.project2.Interfaces.Solver;
import edu.project2.Mazes.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BFSSolve implements Solver {
    private static final int[][] PATH_VARIANTS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private final List<Cell> usedCells = new ArrayList<>();
    Set<Cell> path = new HashSet<>();

    public BFSSolve() {
    }

    @Override
    public List<Cell> solve(Maze maze) {
        Cell start = maze.getGrid()[1][0];
        Cell end = maze.getGrid()[maze.getHeight() - 1][maze.getWidth() - 2];
        Deque<Cell> currentBranch = new ArrayDeque<>();
        Cell current = start;
        currentBranch.push(current);
        usedCells.add(current);
        while (!currentBranch.isEmpty()) {
            path.add(currentBranch.peekLast());
            current = currentBranch.pollLast();
            usedCells.add(current);
            if (current.equals(end)) {
                path.add(current);
                return new ArrayList<>(path);
            }
            var variants = getNeighbour(current, maze);
            variants.forEach(currentBranch::push);
        }
        return new ArrayList<>();
    }

    private List<Cell> getNeighbour(Cell cell, Maze maze) {
        return getPathVariants(cell, maze).stream().filter(c -> !usedCells.contains(c)).toList();
    }

    private List<Cell> getPathVariants(Cell current, Maze maze) {
        List<Cell> variants = new ArrayList<>();
        for (int[] pathVariant : PATH_VARIANTS) {
            int row = current.row() + pathVariant[0];
            int col = current.col() + pathVariant[1];
            if (row > 0 && row < maze.getWidth() && col > 0 && col < maze.getHeight()
                && maze.getGrid()[row][col].type() == Cell.Type.PASSAGE) {
                variants.add(maze.getGrid()[row][col]);
            }
        }
        return variants;
    }
}
