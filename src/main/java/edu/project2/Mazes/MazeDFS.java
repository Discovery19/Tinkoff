package edu.project2.Mazes;

import edu.project2.Cell;
import edu.project2.Interfaces.Generator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class MazeDFS implements Generator {

    private static Maze maze;
    private final int height;
    private final int width;
    private static final Random RANDOM = new Random();
    private static final Deque<Cell> OPEN_CELLS = new ArrayDeque<>();
    private static final int[][] PASSAGES = {{0, -2}, {0, 2}, {-2, 0}, {2, 0}};
    private static List<Cell> rightWay = new ArrayList<>();
    private static final List<Cell> VISITED = new ArrayList<>();

    public MazeDFS(int height, int width) {
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
        maze = new Maze(this.height, this.width);
    }

    public static Cell[][] getGrid() {
        return maze.getGrid();
    }

    @Override
    public void init() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i % 2 != 0 && j % 2 != 0)
                    && (i < height - 1 && j < width - 1)) {
                    getGrid()[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                } else {
                    getGrid()[i][j] = new Cell(i, j, Cell.Type.WALL);
                    VISITED.add(getGrid()[i][j]);
                }
            }
        }
        getGrid()[1][0] = new Cell(1, 0, Cell.Type.PASSAGE);
        getGrid()[height - 1][width - 2] = new Cell(height - 1, width - 2, Cell.Type.PASSAGE);
        VISITED.add(getGrid()[1][0]);
        generate();
    }

    //my solve for DFS
    private static void setRightWay() {
        rightWay = new ArrayList<>(MazeDFS.OPEN_CELLS);
    }

    @Override
    public Maze generate() {

        OPEN_CELLS.push(getGrid()[1][1]);
        VISITED.add(getGrid()[1][1]);

        while (!OPEN_CELLS.isEmpty()) {
            Cell current = OPEN_CELLS.peek();
            //piece of solving DFS)))
            if (current.row() == height - 2 && current.col() == width - 2 && rightWay.isEmpty()) {
                setRightWay();
            }

            Cell neighbour = getNeighbor(current);
            if (neighbour == null) {
                OPEN_CELLS.pop();
                continue;
            }
            OPEN_CELLS.push(neighbour);
            VISITED.add(neighbour);
            int k = -1;
            if (neighbour.col() < current.col() || neighbour.row() < current.row()) {
                k = 1;
            }
            if (neighbour.row() - current.row() == 0) {
                int c = neighbour.col() + k;
                getGrid()[neighbour.row()][c] = new Cell(neighbour.row(), c, Cell.Type.PASSAGE);
            } else {
                int r = neighbour.row() + k;
                getGrid()[r][neighbour.col()] = new Cell(r, neighbour.col(), Cell.Type.PASSAGE);
            }
        }
        return maze;
    }

    private Cell getNeighbor(Cell current) {
        List<Cell> neighbors = new ArrayList<>();
        for (int[] p : PASSAGES) {
            int x = current.row() + p[0];
            int y = current.col() + p[1];

            if (x > 0 && x < height - 1 && y > 0 && y < width - 1 && (getGrid()[x][y].type() == Cell.Type.PASSAGE)
                && !VISITED.contains(getGrid()[x][y])) {
                neighbors.add(getGrid()[x][y]);
            }
        }
        return !neighbors.isEmpty() ? neighbors.get(RANDOM.nextInt(neighbors.size())) : null;
    }
}
