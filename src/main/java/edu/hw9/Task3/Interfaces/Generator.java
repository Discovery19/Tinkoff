package edu.hw9.Task3.Interfaces;

import edu.hw9.Task3.Mazes.Maze;

public interface Generator {
    void init(int height, int width);

    Maze generate(int height, int width);
}
