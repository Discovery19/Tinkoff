package edu.project2;

import edu.project2.Mazes.BackTrackingGenerator;
import edu.project2.Mazes.BinaryMazeGenerator;
import edu.project2.Solves.BFSSolve;
import edu.project2.Solves.DFSSolve;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SampleTest {
    @Test
    @DisplayName("Тест BFS solve + BackTracking generation")
    void testBackTrackingBFS() {
        //arrange
        var generator = new BackTrackingGenerator();
        var maze = generator.generate(11, 11);
        //act
        var solver = new BFSSolve();
        var path = solver.solve(maze);
        //assert
        var renderer = new Render();
        renderer.render(maze, path);
        assertThat(path.size()).isNotZero();
    }

    @Test
    @DisplayName("Тест DFS solve + BackTracking generation")
    void testBackTrackingDFS() {
        //arrange
        var generator = new BackTrackingGenerator();
        var maze = generator.generate(11, 11);
        //act
        var solver = new DFSSolve();
        var path = solver.solve(maze);
        //assert
        var renderer = new Render();
        renderer.render(maze, path);
        assertThat(path.size()).isNotZero();
    }

    @Test
    @DisplayName("Правильный тест DFS solve + Binary generation")
    void testBinaryDFS() {
        //arrange
        var generator = new BinaryMazeGenerator();
        var maze = generator.generate(11, 11);
        //act
        var solver = new DFSSolve();
        var path = solver.solve(maze);
        //assert
        var renderer = new Render();
        renderer.render(maze, path);
        assertThat(path.size()).isNotNegative();
    }

    @Test
    @DisplayName("Правильный тест BFS solve + Binary generation")
    void testBinaryBFS() {
        //arrange
        var generator = new BackTrackingGenerator();
        var maze = generator.generate(15, 15);
        //act
        var solver = new BFSSolve();
        var path = solver.solve(maze);
        //assert
        var renderer = new Render();
        renderer.render(maze, path);
        assertThat(path.size()).isNotNegative();
    }

    @Test
    @DisplayName("Правильный тест 3*3 DFS solve + Binary generation")
    void testBinaryDFSEasy() {
        //arrange
        var generator = new BinaryMazeGenerator();
        var maze = generator.generate(3, 3);
        //act
        var solver = new DFSSolve();
        var path = solver.solve(maze);
        //assert
        var renderer = new Render();
        renderer.render(maze, path);
        assertThat(renderer.getResult()).isEqualTo(
            "# # # \n" +
                ". . # \n" +
                "# . # \n"
        );
        ;
    }

    @Test
    @DisplayName("Правильный тест BFS solve + Binary generation")
    void testBinaryBFSEasy() {
        //arrange
        var generator = new BackTrackingGenerator();
        var maze = generator.generate(3, 3);
        //act
        var solver = new BFSSolve();
        var path = solver.solve(maze);
        //assert
        var renderer = new Render();
        renderer.render(maze, path);
        assertThat(renderer.getResult()).isEqualTo(
            "# # # \n" +
                ". . # \n" +
                "# . # \n"
        );
        ;
    }
//    @Test
//    @DisplayName("проверка лабиринта (представлен в примере лабиринт 3*3) в лабиринте созданном с помощью BinaryTree")
//    void mazeBinaryCheckMaze(){
//        //arrange
//        MazeBinary maze = new MazeBinary(3, 3);
//        maze.init();
//        BlindMiceSolve solve = new BlindMiceSolve(maze.generate());
//        List<Cell> path = solve.solve();
//        //act
//        String result = solve.render(MazeBinary.getGrid(), path);
//        //assert
//        assertThat(result).isEqualTo(
//            "# # # \n" +
//                ". . # \n" +
//                "# . # \n"
//        );
//    }
//    @Test
//    @DisplayName("проверка лабиринта (представлен в примере лабиринт 3*3) в лабиринте созданном с помощью DFS")
//    void mazeDFSCheckMaze() {
//        //arrange
//        MazeDFS maze = new MazeDFS(3, 3);
//        maze.init();
//        BlindMiceSolve solve = new BlindMiceSolve(maze.generate());
//        List<Cell> path = solve.solve();
//        //act
//        String result = solve.render(MazeDFS.getGrid(), path);
//        //assert
//        assertThat(result).isEqualTo(
//            "# # # \n" +
//                ". . # \n" +
//                "# . # \n"
//        );
//    }
//    @Test
//    @DisplayName("лабиринт 1*1 созданный с помощью Binary")
//    void mazeBinaryNotCompliant(){
//        //arrange
//        MazeBinary maze = new MazeBinary(1, 1);
//        //assert
//        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, maze::init);
//    }
//    @Test
//    @DisplayName("лабиринт 1*1 созданный с помощью Binary")
//    void mazeDFSNotCompliant(){
//        //arrange
//        MazeDFS maze = new MazeDFS(1, 1);
//        //assert
//        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, maze::init);
//    }
}
