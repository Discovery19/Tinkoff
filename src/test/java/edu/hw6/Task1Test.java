package edu.hw6;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {
    @Test
    @DisplayName("Существует ли файл ресурсов")
    void isResourceFileExist() {
        //arrange
        File file = new File("src/main/resources/hw6/resourses");
        //assert
        assertThat(file).exists();
    }

    @Test
    @DisplayName("Чтение файла ресурсов")
    void readResourceFile() {
        //arrange
        Task1 task1 = new Task1();
        //act
        task1.readFile(Path.of("src/main/resources/hw6/resourses"));
        var result = task1.getMap();
        //assert
        assertThat(result).isEqualTo(Map.of(
            "1", "new String",
            "2", "another String",
            "a", "something"
        ));
    }

    @Test
    @DisplayName("Существует ли файл для вывода ресурсов")
    void isFileForOutputExist() {
        //arrange
        Task1 task1 = new Task1();
        task1.readFile(Path.of("src/main/resources/hw6/resourses"));
        task1.writeFile(Path.of("src/main/resources/hw6/fileForOutput.txt"));
        //act
        File file = new File("src/main/resources/hw6/fileForOutput.txt");
        //assert
        assertThat(file).exists();
        file.delete();
    }
    @Test
    @DisplayName("Проверка правильности вывода ресурсов")
    void isFileForOutputRight() {
        //arrange
        Task1 task1 = new Task1();
        task1.readFile(Path.of("src/main/resources/hw6/resourses"));
        task1.writeFile(Path.of("src/main/resources/hw6/fileForOutput.txt"));
        //act
        task1.readFile(Path.of("src/main/resources/hw6/fileForOutput.txt"));
        var result = task1.getMap();
        //assert
        assertThat(result).isEqualTo(Map.of(
            "1", "new String",
            "2", "another String",
            "a", "something"
        ));
        new File("src/main/resources/hw6/fileForOutput.txt").delete();
    }
}
