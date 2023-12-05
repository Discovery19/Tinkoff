package edu.hw9.Task3.Solves;

import edu.hw9.Task3.Cell;
import edu.hw9.Task3.Interfaces.Solver;
import edu.hw9.Task3.Mazes.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadDFSSolve implements Solver {
    private static final int[][] PATH_VARIANTS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private final Random random = new Random();
    private final int numThreads;

    public MultiThreadDFSSolve(int numThreads) {
        this.numThreads = numThreads;
    }

    @Override
    public List<Cell> solve(Maze maze) {
        List<Cell> path = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        // Start DFS from each segment
        for (int i = 0; i < numThreads; i++) {
            executorService.execute(() -> {
                List<Cell> segmentPath = startDFS(maze, maze.getHeight() - 1, maze.getWidth() - 2);
                if (!segmentPath.isEmpty()) {
                    path.addAll(segmentPath);
                }
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException ignored) {
        }

        return path;
    }

    private List<Cell> startDFS(Maze maze, int endRow, int endCol) {
        List<Cell> segmentPath = new ArrayList<>();
        Cell end = maze.getGrid()[endRow][endCol];

        for (int row = 1; row <= endRow; row++) {
            for (int col = 0; col <= endCol; col++) {
                Cell start = maze.getGrid()[row][col];
                if (start.type() == Cell.Type.WALL) {
                    continue;
                }

                List<Cell> currentBranch = new ArrayList<>();
                currentBranch.add(start);

                while (!currentBranch.isEmpty()) {
                    Cell current = currentBranch.get(currentBranch.size() - 1);

                    if (current.equals(end)) {
                        segmentPath.addAll(currentBranch);
                        return segmentPath;
                    }

                    Cell neighbour = getNeighbour(current, maze);
                    if (neighbour == null || currentBranch.contains(neighbour)) {
                        currentBranch.remove(currentBranch.size() - 1);
                    } else {
                        currentBranch.add(neighbour);
                    }
                }
            }
        }
        return segmentPath;
    }

    private Cell getNeighbour(Cell cell, Maze maze) {
        List<Cell> variants = getPathVariants(cell, maze);
        return !variants.isEmpty() ? variants.get(random.nextInt(variants.size())) : null;
    }

    private List<Cell> getPathVariants(Cell current, Maze maze) {
        List<Cell> variants = new ArrayList<>();
        for (int[] pathVariant : PATH_VARIANTS) {
            int row = current.row() + pathVariant[0];
            int col = current.col() + pathVariant[1];
            if (row >= 0 && col >= 0 && row < maze.getHeight() && col < maze.getWidth()
                && maze.getGrid()[row][col].type() == Cell.Type.PASSAGE) {
                variants.add(maze.getGrid()[row][col]);
            }
        }
        return variants;
    }
}
