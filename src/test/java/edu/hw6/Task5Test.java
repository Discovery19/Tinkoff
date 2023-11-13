package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import static org.assertj.core.api.Assertions.assertThat;

class Task5Test {
    @Test
    @DisplayName("Проверка по размеру")
    void hackerNewsTopStories() throws URISyntaxException, IOException, InterruptedException {
        //act
        long[] result= Task5.hackerNewsTopStories();
        //assert
        assertThat(result).hasSize(500);
    }
    @Test
    @DisplayName("Проврка элемента")
    void hackerNewsTopStoriesFirst() throws URISyntaxException, IOException, InterruptedException {
        //act
        long[] result= Task5.hackerNewsTopStories();
        //assert
        assertThat(result).contains(38251330L);
    }
    @Test
    @DisplayName("Проверка текста")
    void news() throws URISyntaxException, IOException, InterruptedException {
        //act
        String result= Task5.news();
        //assert
        assertThat(result).isEqualTo("JDK 21 Release Notes");
    }
}
