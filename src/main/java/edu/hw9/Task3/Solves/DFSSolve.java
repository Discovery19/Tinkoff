package edu.hw9.Task3.Solves;

import edu.hw9.Task3.Cell;
import edu.hw9.Task3.Interfaces.Solver;
import edu.hw9.Task3.Mazes.Maze;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DFSSolve implements Solver {
    private static final int[][] PATH_VARIANTS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private final List<Cell> usedCells = new ArrayList<>();
    private final Random random = new Random();
    Deque<Cell> currentBranch = new ArrayDeque<>();
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    boolean pathFound = false;
    ReadWriteLock lock = new ReentrantReadWriteLock();
    @Override
    public List<Cell> solve(Maze maze) {

        while (!pathFound){
            executorService.execute(()->worker(maze));
        }
        executorService.shutdown();
        executorService.close();
        return currentBranch.stream().toList();
    }
    private List<Cell> worker(Maze maze){
        Cell end = maze.getGrid()[maze.getHeight() - 1][maze.getWidth() - 2];

        Cell current = maze.getGrid()[1][0];
        currentBranch.add(current);
        while (!currentBranch.isEmpty() && !executorService.isShutdown()) {
            current = currentBranch.peek();
            if (current.equals(end)) {
                pathFound = true;
                return new ArrayList<>(currentBranch);
            }

            Cell neighbour = getNeighbour(current, maze);
            if (neighbour == null) {
                lock.writeLock().lock();
                try {
                    currentBranch.pop();
                }
                finally {
                    lock.writeLock().unlock();
                }
                continue;
            }
            try {
                currentBranch.push(neighbour);
            }
            finally {
                lock.writeLock().unlock();
            }

            //usedCells.add(neighbour);
        }
        return new ArrayList<>();
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
            if (row > 0 && col > 0
                && maze.getGrid()[row][col].type() == Cell.Type.PASSAGE) {
                variants.add(maze.getGrid()[row][col]);
            }
        }
        return variants;
    }
}
