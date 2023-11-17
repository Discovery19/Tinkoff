package edu.project2.Interfaces;

import edu.project2.Cell;
import edu.project2.Mazes.Maze;
import java.util.List;

public interface Solver {
    List<Cell> solve(Maze maze);
//    List<Cell> sol(Maze maze, Cell start, Cell end);
//    default Cell checkStart(Cell start){
//        if (start.col()%2>0 && start.row()==0) return start;
//        else if ((start.col()&2)==0) { return new Cell(start.col()+1, start.row(), Cell.Type.PASSAGE);
//        }
//        else { return new Cell(start.col(), 0, Cell.Type.PASSAGE);}
//    }
//    default Cell checkEnd(Cell end);
}
