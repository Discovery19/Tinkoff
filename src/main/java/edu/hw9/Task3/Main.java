package edu.hw9.Task3;

import edu.hw9.Task3.Mazes.BackTrackingGenerator;
import edu.hw9.Task3.Solves.DFSSolve;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(() -> {
            var generator = new BackTrackingGenerator();
            var maze = generator.generate(11, 11);
            var solver = new DFSSolve();
            var path = solver.solve(maze);
            System.out.println("pathSolve");
            //var path = solver.getUsedCells();
            var renderer = new Render();
            System.out.println("render");
            renderer.render(maze, path.stream().toList());
        });

        //assert
        try {
            future.get(); // Ждем завершения задачи
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        //assert
        ExecutorService execut = new ForkJoinPool();

    }
}
