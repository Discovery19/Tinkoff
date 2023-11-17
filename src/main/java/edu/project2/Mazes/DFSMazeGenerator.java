package edu.project2.Mazes;

import edu.project2.Cell;
import edu.project2.Interfaces.Generator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

//оставил тк хоть он и несимпатичен, но универсален (во время поиска пути генерирует лпбирит))))
public class DFSMazeGenerator implements Generator {
    private static final Random RANDOM = new Random();
    private final Deque<Cell> openCells = new ArrayDeque<>();
    private final int[][] passages = {{0, -2}, {0, 2}, {-2, 0}, {2, 0}};
    private List<Cell> rightWay = new ArrayList<>();
    private final List<Cell> visited = new ArrayList<>();

    private Maze maze;
    private int height;
    private int width;
    private Cell[][] grid;

    public DFSMazeGenerator() {

    }

    @Override
    public void init(int height, int width) {
        if (height % 2 == 0) {
            this.height = height + 1;
        } else {
            this.height = height;
        }
        if (width % 2 == 0) {
            this.width = width + 1;
        } else {
            this.width = width;
        }
        this.maze = new Maze(this.height, this.width);
        this.grid = maze.getGrid();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i % 2 != 0 && j % 2 != 0)
                    && (i < height - 1 && j < width - 1)) {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.WALL);
                    visited.add(grid[i][j]);
                }
            }
        }
        grid[1][0] = new Cell(1, 0, Cell.Type.PASSAGE);
        grid[height - 1][width - 2] = new Cell(height - 1, width - 2, Cell.Type.PASSAGE);
        visited.add(grid[1][0]);
    }

    //my solve for DFS
    private void setRightWay() {
        rightWay = new ArrayList<>(openCells);
    }

    @Override
    public Maze generate(int height, int width) {
        init(height, width);
        openCells.push(grid[1][1]);
        visited.add(grid[1][1]);

        while (!openCells.isEmpty()) {
            Cell current = openCells.peek();
            //piece of solving DFS)))
            if (current.row() == height - 2 && current.col() == width - 2 && rightWay.isEmpty()) {
                setRightWay();
            }

            Cell neighbour = getNeighbor(current);
            if (neighbour == null) {
                openCells.pop();
                continue;
            }
            openCells.push(neighbour);
            visited.add(neighbour);
            int k = -1;
            if (neighbour.col() < current.col() || neighbour.row() < current.row()) {
                k = 1;
            }
            if (neighbour.row() - current.row() == 0) {
                int c = neighbour.col() + k;
                grid[neighbour.row()][c] = new Cell(neighbour.row(), c, Cell.Type.PASSAGE);
            } else {
                int r = neighbour.row() + k;
                grid[r][neighbour.col()] = new Cell(r, neighbour.col(), Cell.Type.PASSAGE);
            }
        }
        return maze;
    }

    private Cell getNeighbor(Cell current) {
        List<Cell> neighbors = new ArrayList<>();
        for (int[] p : passages) {
            int x = current.row() + p[0];
            int y = current.col() + p[1];

            if (x > 0 && x < height - 1 && y > 0 && y < width - 1 && (grid[x][y].type() == Cell.Type.PASSAGE)
                && !visited.contains(grid[x][y])) {
                neighbors.add(grid[x][y]);
            }
        }
        return !neighbors.isEmpty() ? neighbors.get(RANDOM.nextInt(neighbors.size())) : null;
    }
}
