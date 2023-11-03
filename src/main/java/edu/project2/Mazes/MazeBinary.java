package edu.project2.Mazes;

import edu.project2.Cell;
import edu.project2.Interfaces.Generator;
import java.util.Random;

public class MazeBinary implements Generator {
    private static Maze maze;
    private final int height;
    private final int width;
    private static final Random RANDOM = new Random();
    private static final int[][] RANDOM_BASH_WALL = {{-1, 0}, {0, 1}};
    private static final int[][] BREAK_FLOOR = {{0, 0}, {1, 0}};

    public MazeBinary(int height, int width) {
        this.height = height;
        this.width = width;
        this.maze = new Maze(height, width);
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
                }
            }
        }
        getGrid()[1][0] = new Cell(1, 0, Cell.Type.PASSAGE);
        getGrid()[height - 1][width - 2] = new Cell(height - 1, width - 2, Cell.Type.PASSAGE);
    }

    @Override
    public Maze generate() {
        for (int i = 1; i < height - 1; i += 2) {
            int j = 1;
            while (j < width - 1) {
                int[] wall = RANDOM_BASH_WALL[RANDOM.nextInt(RANDOM_BASH_WALL.length)];
                if (i + wall[0] > 0 && j + wall[1] > 0 && j + wall[1] < width - 1) {
                    getGrid()[i + wall[0]][j + wall[1]] = new Cell(i + wall[0], j + wall[1], Cell.Type.PASSAGE);
                }
                j += 2;
            }
            j -= 2;
            while (j > 0) {
                int[] wall = BREAK_FLOOR[RANDOM.nextInt(BREAK_FLOOR.length)];
                if (i + wall[0] > 0 && i + wall[0] < height - 1) {
                    getGrid()[i + wall[0]][j] = new Cell(i + wall[0], j, Cell.Type.PASSAGE);
                }
                j -= 2;
            }
        }
        return maze;
    }
}

