package edu.project2.Interfaces;

import edu.project2.Mazes.Maze;

public interface Generator {
    void init(int height, int width);

    Maze generate(int height, int width);
}
