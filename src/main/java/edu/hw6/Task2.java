package edu.hw6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Task2 {
    public static void main(String[] args) {
        cloneFile(Path.of("src/main/resources/hw6/Tinkoff Bank Biggest Secret.txt"));
    }
    public static void cloneFile(Path path) {
        File originalFile = path.toFile();
        String originalFileName = originalFile.getName();
        String originalName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        int copyNumber = 0;
        File newFile = new File(path.getParent().toString() + "\\" + originalName + extension);

        while (newFile.exists()) {
            copyNumber++;
            newFile = new File(path.getParent().toString() + "\\" + originalName + " — копия (" + copyNumber + ")" + extension);
        }

        try {
            Files.copy(path, newFile.toPath());
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
    }
}
