package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class DirectoriesTask {
    private final File directory;
    private final int fileNum;

    public DirectoriesTask(File directory, int fileNum) {
        this.directory = directory;
        this.fileNum = fileNum;
    }

    public int searchDirs() {
        try (ForkJoinPool pool = new ForkJoinPool()) {
            return pool.invoke(new FilterTask(directory, fileNum));
        }
    }

    private static class FilterTask extends RecursiveTask<Integer> {
        File directory;
        int fileNum;

        FilterTask(File directory, int fileNum) {
            this.directory = directory;
            this.fileNum = fileNum;
        }

        @Override
        protected Integer compute() {
            int count = 0;
            List<FilterTask> subtasks = new ArrayList<>();
            File[] subDirs = directory.listFiles(File::isDirectory);
            if (subDirs != null) {
                for (File subDir : subDirs) {
                    FilterTask subtask = new FilterTask(subDir, fileNum);
                    subtasks.add(subtask);
                    subtask.fork();
                }
            }
            for (FilterTask subtask : subtasks) {
                count += subtask.join();
            }

            File[] files = directory.listFiles();
            if (files != null && files.length > fileNum) {
                count++;
            }

            return count;
        }
    }
}
