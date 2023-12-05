package edu.hw9;

import edu.hw9.Task3.Mazes.BackTrackingGenerator;
import edu.hw9.Task3.Render;
import edu.hw9.Task3.Solves.MultiThreadDFSSolve;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {
    @Test
    @DisplayName("Simple Test")
    void test(){
        //arrange//act//assert
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            var generator = new BackTrackingGenerator();
            var maze = generator.generate(11, 11);

            var solver = new MultiThreadDFSSolve(4);
            var path = solver.solve(maze);

            executorService.shutdown();
            assertThat(path).isNotEmpty();
            var renderer = new Render();
            renderer.render(maze, path);
        });

    }
}
