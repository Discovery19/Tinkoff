package edu.hw6;

import java.io.File;
import java.nio.file.Path;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task2Test {

    @Test
    @Disabled
    @DisplayName("Создание копии файла")
    void testOneCopy() {
        //arrange
        new Task2().cloneFile(Path.of("src/main/resources/hw6/Tinkoff Bank Biggest Secret.txt"));
        //act
        File file = new File("src/main/resources/hw6/Tinkoff Bank Biggest Secret — копия.txt");
        //assert
        assertThat(true).isTrue();
        assertThat(file).exists();
        file.delete();
    }

    @Test
    @Disabled
    @DisplayName("Создание нескольких копий")
    void testTwoOrMoreCopies() {
        //arrange
        Task2 task2 = new Task2();
        task2.cloneFile(Path.of("src/main/resources/hw6/Tinkoff Bank Biggest Secret.txt"));
        task2.cloneFile(Path.of("src/main/resources/hw6/Tinkoff Bank Biggest Secret.txt"));
        //act
        File file1 = new File("src/main/resources/hw6/Tinkoff Bank Biggest Secret — копия.txt");
        File file2 = new File("src/main/resources/hw6/Tinkoff Bank Biggest Secret — копия (1).txt");
        //assert
        assertThat(true).isTrue();
        assert (file1.exists() && file2.exists());
        file1.delete();
        file2.delete();
    }
}
