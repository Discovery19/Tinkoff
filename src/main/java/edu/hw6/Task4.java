package edu.hw6;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

@Slf4j
public class Task4 {
    private static final Path PATH = Path.of("src/main/resources/hw6/fileForTask4.txt");

    public static OutputStream createFile(Path path) throws IOException {
        Files.createFile(path);
        return Files.newOutputStream(path);
    }

    public static CheckedOutputStream checkStream(OutputStream outputStream) {
        return new CheckedOutputStream(outputStream, new CRC32());
    }

    public static BufferedOutputStream addBuffer(OutputStream outputStream) {
        return new BufferedOutputStream(outputStream);
    }

    public static OutputStreamWriter addOutputStreamWriter(OutputStream outputStream) {
        return new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
    }

    public static PrintWriter addPrintWriter(Writer writer) {
        return new PrintWriter(writer);
    }

    public static void writeToFile() {
        try (OutputStream outputStream = createFile(PATH);
             CheckedOutputStream checkedOutputStream = checkStream(outputStream);
             BufferedOutputStream bufferedOutputStream = addBuffer(checkedOutputStream);
             OutputStreamWriter outputStreamWriter = addOutputStreamWriter(bufferedOutputStream);
             PrintWriter printWriter = addPrintWriter(outputStreamWriter)) {
            printWriter.print("Programming is learned by writing programs. ― Brian Kernighan");

        } catch (IOException e) {
            log.warn(e.getMessage());
        }
    }
}
