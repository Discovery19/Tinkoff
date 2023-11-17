package edu.project2.Mazes;

import edu.project2.Cell;
import edu.project2.Interfaces.Generator;
import java.util.Random;

public class BinaryMazeGenerator implements Generator {
    private static final Random RANDOM = new Random();
    private static final int[][] BREAK_WALL = {{-1, 0}, {0, 1}};
    private static final int[][] BREAK_FLOOR = {{0, 0}, {1, 0}};
    private Maze maze;
    private Cell[][] grid;

    public BinaryMazeGenerator() {
    }


    @Override
    public void init(int h, int w) {
        int height;
        int width;
        if (h % 2 == 0) {
            height = h + 1;
        } else {
            height = h;
        }
        if (w % 2 == 0) {
            width = w + 1;
        } else {
            width = w;
        }
        this.maze = new Maze(height, width);
        this.grid = maze.getGrid();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i % 2 != 0 && j % 2 != 0)
                    && (i < height - 1 && j < width - 1)) {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.WALL);
                }
            }
        }
        grid[1][0] = new Cell(1, 0, Cell.Type.PASSAGE);
        grid[height - 1][width - 2] = new Cell(height - 1, width - 2, Cell.Type.PASSAGE);
    }

    @Override
    public Maze generate(int height, int width) {
        init(height, width);
        for (int i = 1; i < height - 1; i += 2) {
            int j = 1;
            while (j < width - 1) {
                int[] wall = BREAK_WALL[RANDOM.nextInt(BREAK_WALL.length)];
                if (i + wall[0] > 0 && j + wall[1] > 0 && j + wall[1] < width - 1) {
                    grid[i + wall[0]][j + wall[1]] = new Cell(i + wall[0], j + wall[1], Cell.Type.PASSAGE);
                }
                j += 2;
            }
            j -= 2;
            while (j > 0) {
                int[] wall = BREAK_FLOOR[RANDOM.nextInt(BREAK_FLOOR.length)];
                if (i + wall[0] > 0 && i + wall[0] < height - 1) {
                    grid[i + wall[0]][j] = new Cell(i + wall[0], j, Cell.Type.PASSAGE);
                }
                j -= 2;
            }
        }
        return maze;
    }
}

