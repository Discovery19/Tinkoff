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
    @DisplayName("Добавление в мап")
    void put() {
        //arrange
        Task1 task1 = new Task1(Path.of("src/main/resources/hw6/task1/map"));
        //act
        task1.put("1", "new String");
        var result = task1.containsKey("1");
        //assert
        assertThat(result).isTrue();
        task1.clear();
    }
    @Test
    @DisplayName("Добавление в мап перезаписывание")
    void putAnotherValue() {
        //arrange
        Task1 task1 = new Task1(Path.of("src/main/resources/hw6/task1/map"));
        //act
        task1.put("1", "new String");
        task1.put("2", "new");
        task1.put("1", "string");
        var result = task1.get("1");
        //assert
        assertThat(result).isEqualTo("string");
        task1.clear();
    }
    @Test
    @DisplayName("Удаление из мап")
    void remove() {
        //arrange
        Task1 task1 = new Task1(Path.of("src/main/resources/hw6/task1/map"));
        //act
        task1.put("1", "new String");
        task1.remove("1");
        //assert
        assertThat(task1.size()).isZero();
        task1.clear();
    }
    @Test
    @DisplayName("Есть ключ")
    void containsKey() {
        //arrange
        Task1 task1 = new Task1(Path.of("src/main/resources/hw6/task1/map"));
        //act
        task1.put("1", "new String");
        task1.put("2", "new String");
        //assert
        assertThat(task1.containsKey("1")).isTrue();
        task1.clear();
    }
    @Test
    @DisplayName("Есть значение")
    void containsValue() {
        //arrange
        Task1 task1 = new Task1(Path.of("src/main/resources/hw6/task1/map"));
        //act
        task1.put("1", "new String");
        task1.put("2", "str");
        task1.put("3", "new String");
        System.out.println(task1.get("1"));
        var result = task1.containsValue("str");
        //assert
        assertThat(result).isTrue();
        task1.clear();
    }
    @Test
    @DisplayName("put all")
    void putAll() {
        //arrange
        Task1 task1 = new Task1(Path.of("src/main/resources/hw6/task1/map"));
        //act
        task1.put("1", "new String");
        task1.put("2", "str");
        task1.putAll(Map.of("3", "new String"));
        var result = task1.size();
        //assert
        assertThat(result).isEqualTo(3);
        task1.clear();
    }
}
