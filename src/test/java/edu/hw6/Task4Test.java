package edu.hw6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {
    @Test
    @DisplayName("Проверка файла")
    void fileExist() {
        //act
        Task4 task4 = new Task4();
        task4.writeToFile();
        File file = new File(String.valueOf(task4.getPath()));
        //assert
        assertThat(file).exists();
        file.delete();
    }
    @Test
    @DisplayName("Проверка текста в файле")
    void fileTextTest() throws IOException {
        //arrange
        Task4 task4 = new Task4();
        task4.writeToFile();
        File file = new File(String.valueOf(task4.getPath()));
        //act
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String result = reader.readLine();
        reader.close();
        //assert
        assertThat(result).isEqualTo("Programming is learned by writing programs. ― Brian Kernighan");
        file.delete();
    }
}
