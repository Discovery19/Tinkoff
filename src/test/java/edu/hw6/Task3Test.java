package edu.hw6;

import edu.hw6.Task3.Task3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {
    @Test
    @DisplayName("Стандратная фильтрация readable")
    void filterReadable() {
        //arrange
        Task3 task3 = new Task3();
        DirectoryStream.Filter<Path> filter = task3.readable;
        List<Path> result = new ArrayList<>();
        //act
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/main/resources/hw6/task3"), filter)) {
            entries.forEach(result::add);
        } catch (IOException ignored) {
        }
        //assert
        assertThat(result).isEqualTo(List.of(
            Path.of("src/main/resources/hw6/task3/1.txt"),
            Path.of("src/main/resources/hw6/task3/file-file.txt"),
            Path.of("src/main/resources/hw6/task3/my_file.doc")
            ));
    }
    @Test
    @DisplayName("Стандратная фильтрация writable")
    void filterWritable() {
        //arrange
        Task3 task3 = new Task3();
        DirectoryStream.Filter<Path> filter = task3.writable;
        List<Path> result = new ArrayList<>();
        //act
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/main/resources/hw6/task3"), filter)) {
            entries.forEach(result::add);
        } catch (IOException ignored) {
        }
        //assert
        assertThat(result).isEqualTo(List.of(
            Path.of("src/main/resources/hw6/task3/1.txt"),
            Path.of("src/main/resources/hw6/task3/file-file.txt"),
            Path.of("src/main/resources/hw6/task3/my_file.doc")
        ));
    }
    @Test
    @DisplayName("Стандратная фильтрация .doc")
    void filterGlobMatches() {
        //arrange
        Task3 task3 = new Task3();
        DirectoryStream.Filter<Path> filter = task3.globMatching(".doc");
        List<Path> result = new ArrayList<>();
        //act
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/main/resources/hw6/task3"), filter)) {
            entries.forEach(result::add);
        } catch (IOException ignored) {
        }
        //assert
        assertThat(result).isEqualTo(List.of(
            Path.of("src/main/resources/hw6/task3/my_file.doc")
        ));
    }
    @Test
    @DisplayName("Стандратная фильтрация regex")
    void filterRegex() {
        //arrange
        Task3 task3 = new Task3();
        DirectoryStream.Filter<Path> filter = task3.regex("my_file.doc");
        List<Path> result = new ArrayList<>();
        //act
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/main/resources/hw6/task3"), filter)) {
            entries.forEach(result::add);
        } catch (IOException ignored) {
        }
        //assert
        assertThat(result).isEqualTo(List.of(
            Path.of("src/main/resources/hw6/task3/my_file.doc")
        ));
    }
    @Test
    @DisplayName("Стандратная фильтрация size")
    void filterSize() {
        //arrange
        Task3 task3 = new Task3();
        DirectoryStream.Filter<Path> filter = task3.sizeFilter(10);
        List<Path> result = new ArrayList<>();
        //act
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/main/resources/hw6/task3"), filter)) {
            entries.forEach(result::add);
        } catch (IOException ignored) {
        }
        //assert
        assertThat(result).isEqualTo(List.of(
            Path.of("src/main/resources/hw6/task3/1.txt")
        ));
    }
    @Test
    @DisplayName("Стандратная фильтрация magic and glob")
    void filterMagicAndRegex() {
        //arrange
        Task3 task3 = new Task3();
        DirectoryStream.Filter<Path> filter = task3.magicNumbers(new String[]{"-"}).and(task3.globMatching(".txt"));
        List<Path> result = new ArrayList<>();
        //act
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/main/resources/hw6/task3"), filter)) {
            entries.forEach(result::add);
        } catch (IOException ignored) {
        }
        //assert
        assertThat(result).isEqualTo(List.of(
            Path.of("src/main/resources/hw6/task3/file-file.txt")
        ));
    }
}
