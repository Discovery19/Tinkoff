package edu.project2;

import edu.project2.Mazes.Maze;
import java.util.List;

public class Render {
    private StringBuilder sb = new StringBuilder();

    public Render() {
    }

    public String getResult() {
        return sb.toString();
    }

    public void render(Maze maze, List<Cell> path) {
        Cell[][] grid = maze.getGrid();
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
        //CHECKSTYLE:OFF: checkstyle:RegexpSinglelineJava
        System.out.println(sb);
    }
}
