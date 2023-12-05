package edu.hw9;

import edu.hw9.Task2.DirectoriesTask;
import edu.hw9.Task2.FilesTask;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {
    @Test
    @DisplayName("Test DirectoryTask")
    void testDirectoryTask(){
        //arrange
        File rootDir = new File("src/main/resources/hw9");
        //act
        DirectoriesTask task = new DirectoriesTask(rootDir, 5);
        int res = task.searchDirs();
        //assert
        assertThat(res).isOne();
    }
    @Test
    @DisplayName("Test FilesTask")
    void testFilesTask(){
        //arrange
        String targetExtension = ".txt";
        int targetSize = 100;
        File rootDir = new File("src/main/resources/hw9");
        //act
        FilesTask task = new FilesTask(rootDir, targetExtension, targetSize);
        List<File> res = task.searchFiles();
        //assert
        assertThat(res.getFirst().toString()).hasToString("src\\main\\resources\\hw9\\6.txt");
    }
}
