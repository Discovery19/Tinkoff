package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FilesTask {
    private final File directory;
    private final String targetExtension;
    private final int targetSize;

    public FilesTask(File directory, String targetExtension, int targetSize) {
        this.directory = directory;
        this.targetExtension = targetExtension;
        this.targetSize = targetSize;
    }

    public List<File> searchFiles() {
        List<File> files = Arrays.stream(Objects.requireNonNull(directory.listFiles())).toList();
        try (ForkJoinPool pool = new ForkJoinPool()) {
            return pool.invoke(new FilterTask(files, targetExtension, targetSize));
        }
    }

    private static class FilterTask extends RecursiveTask<List<File>> {
        private final List<File> inputArray;
        private final String targetExtension;
        private final int targetSize;
        private final int minSize = 10;

        FilterTask(List<File> inputArray, String targetExtension, int targetSize) {
            this.inputArray = inputArray;
            this.targetExtension = targetExtension;
            this.targetSize = targetSize;
        }

        @Override
        protected List<File> compute() {
            int size = inputArray.size();
            if (size <= minSize) {
                List<File> subResult = new ArrayList<>();
                for (File file : inputArray) {
                    if (file.getName().endsWith(targetExtension) && file.length() > targetSize) {
                        subResult.add(file);
                    }
                }
                return subResult;
            } else {
                int middle = size / 2;
                FilterTask leftTask =
                    new FilterTask(inputArray.subList(0, middle), targetExtension, targetSize);
                FilterTask rightTask =
                    new FilterTask(inputArray.subList(middle, size), targetExtension, targetSize);

                invokeAll(leftTask, rightTask);

                List<File> leftResult = leftTask.join();
                List<File> rightResult = rightTask.join();
                leftResult.addAll(rightResult);

                return leftResult;
            }
        }
    }
}
