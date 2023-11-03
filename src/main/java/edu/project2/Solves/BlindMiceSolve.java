package edu.project2.Solves;

import edu.project2.Cell;
import edu.project2.Interfaces.Solver;
import edu.project2.Mazes.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class BlindMiceSolve implements Solver {
    private static final int[][] PATH_VARIANTS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private final List<Cell> usedCells = new ArrayList<>();
    private final Random random = new Random();
    private final Maze maze;
    private final Cell start;
    private final Cell end;

    public BlindMiceSolve(Maze maze) {
        this.maze = maze;
        this.start = maze.getGrid()[1][0];
        this.end = maze.getGrid()[maze.getHeight() - 1][maze.getWidth() - 2];
    }

    List<Cell> openedCells = new ArrayList<>();

    @Override
    public List<Cell> solve() {
        Deque<Cell> cells = new ArrayDeque<>();
        Cell current = start;
        cells.push(current);
        openedCells.add(current);
        usedCells.add(current);
        while (!cells.isEmpty()) {
            Cell next = getNeighbour(current);
            if (next != null) {
                cells.push(current);
                usedCells.add(next);
                current = next;
                if (current.equals(end)) {
                    break;
                }
            } else {
                current = cells.pop();
            }
        }
        if (!cells.isEmpty()) {
            cells.add(end);
            return new ArrayList<>(cells);
        }
        return new ArrayList<>();
    }

    private List<Cell> getPathVariants(Cell current) {
        List<Cell> variants = new ArrayList<>();
        for (int[] pathVariant : PATH_VARIANTS) {
            int row = current.row() + pathVariant[0];
            int col = current.col() + pathVariant[1];
            if (row > 0 && row < maze.getWidth() && col > 0 && col < maze.getHeight()
                && maze.getGrid()[row][col].type() == Cell.Type.PASSAGE
                && !openedCells.contains(maze.getGrid()[row][col]) && !usedCells.contains(maze.getGrid()[row][col])) {
                variants.add(maze.getGrid()[row][col]);
            }
        }
        return variants;
    }

    private Cell getNeighbour(Cell cell) {
        List<Cell> variants = getPathVariants(cell);
        return !variants.isEmpty() ? variants.get(random.nextInt(variants.size())) : null;
    }

    @Override
    public String render(Cell[][] grid, List<Cell> path) {
        StringBuilder sb = new StringBuilder();
        for (Cell[] i : grid) {
            for (Cell j : i) {
                if (path.contains(j)) {
                    sb.append(". ");
                } else if (j.type() == Cell.Type.PASSAGE) {
                    sb.append("  ");
                } else {
                    sb.append("# ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
