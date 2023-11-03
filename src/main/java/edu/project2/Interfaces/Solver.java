package edu.project2.Interfaces;

import edu.project2.Cell;
import java.util.List;

public interface Solver extends Renderer {
    List<Cell> solve();
}
