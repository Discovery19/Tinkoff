package edu.hw6;
//CHECKSTYLE:OFF: checkstyle:ImportOrder
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.io.BufferedOutputStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task4 {
    public Task4() {
    }

    private static final Path PATH = Path.of("src/main/resources/hw6/fileForTask4.txt");

    public Path getPath() {
        return PATH;
    }

    private OutputStream createFile() throws IOException {
        Files.createFile(Task4.PATH);
        return Files.newOutputStream(Task4.PATH);
    }

    private CheckedOutputStream checkStream(OutputStream outputStream) {
        return new CheckedOutputStream(outputStream, new CRC32());
    }

    private BufferedOutputStream addBuffer(OutputStream outputStream) {
        return new BufferedOutputStream(outputStream);
    }

    private OutputStreamWriter addOutputStreamWriter(OutputStream outputStream) {
        return new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
    }

    private PrintWriter addPrintWriter(Writer writer) {
        return new PrintWriter(writer);
    }

    public void writeToFile() {
        try (OutputStream outputStream = createFile();
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
