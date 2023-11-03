package edu.project2;

import edu.project2.Mazes.MazeBinary;
import edu.project2.Mazes.MazeDFS;
import edu.project2.Solves.BlindMiceSolve;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class SampleTest {
    @Test
    @DisplayName("Нахождения пути с помощью метода случайной мыши в лабиринте созданном с помощью DFS")
    void mazeDFS(){
        //arrange
        MazeDFS maze = new MazeDFS(11, 11);
        maze.init();
        BlindMiceSolve solve = new BlindMiceSolve(maze.generate());
        List<Cell> path = solve.solve();
        //act
        System.out.println(solve.render(MazeDFS.getGrid(),path));
        //assert
        assertThat(path.size()).isNotZero();
    }
    @Test
    @DisplayName("Нахождения пути(представлен в примере лабиринт 3*3, точно имеющий решение) с помощью метода случайной мыши в лабиринте созданном с помощью BinaryTree")
    void mazeBinary(){
        //arrange
        MazeBinary maze = new MazeBinary(3, 3);
        maze.init();
        BlindMiceSolve solve = new BlindMiceSolve(maze.generate());
        List<Cell> path = solve.solve();
        //act
        System.out.println(solve.render(MazeBinary.getGrid(), path));
        //assert
        assertThat(path.size()).isNotZero();
    }
    @Test
    @DisplayName("проверка лабиринта (представлен в примере лабиринт 3*3) в лабиринте созданном с помощью BinaryTree")
    void mazeBinaryCheckMaze(){
        //arrange
        MazeBinary maze = new MazeBinary(3, 3);
        maze.init();
        BlindMiceSolve solve = new BlindMiceSolve(maze.generate());
        List<Cell> path = solve.solve();
        //act
        String result = solve.render(MazeBinary.getGrid(), path);
        //assert
        assertThat(result).isEqualTo(
            "# # # \n" +
                ". . # \n" +
                "# . # \n"
        );
    }
    @Test
    @DisplayName("проверка лабиринта (представлен в примере лабиринт 3*3) в лабиринте созданном с помощью DFS")
    void mazeDFSCheckMaze() {
        //arrange
        MazeDFS maze = new MazeDFS(3, 3);
        maze.init();
        BlindMiceSolve solve = new BlindMiceSolve(maze.generate());
        List<Cell> path = solve.solve();
        //act
        String result = solve.render(MazeDFS.getGrid(), path);
        //assert
        assertThat(result).isEqualTo(
            "# # # \n" +
                ". . # \n" +
                "# . # \n"
        );
    }
    @Test
    @DisplayName("лабиринт 1*1 созданный с помощью Binary")
    void mazeBinaryNotCompliant(){
        //arrange
        MazeBinary maze = new MazeBinary(1, 1);
        //assert
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, maze::init);
    }
    @Test
    @DisplayName("лабиринт 1*1 созданный с помощью Binary")
    void mazeDFSNotCompliant(){
        //arrange
        MazeDFS maze = new MazeDFS(1, 1);
        //assert
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, maze::init);
    }
}
