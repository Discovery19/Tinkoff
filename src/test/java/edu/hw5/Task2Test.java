package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {
    @Test
    @DisplayName("Все черные пятницы в 1925")
    void testAllBlackFridays1925() {
        //arrange/act
        List<String> result = Task2.allBlackFridays(1925);
        //assert
        assertThat(result).contains("1925-02-13", "1925-03-13", "1925-11-13");
    }

    @Test
    @DisplayName("Все черные пятницы в 2024")
    void testAllBlackFridays2024() {
        //arrange/act
        List<String> result = Task2.allBlackFridays(2024);
        //assert
        assertThat(result).contains("2024-09-13", "2024-12-13");
    }
    @Test
    @DisplayName("Ближайшая пятница 13 для 13.08, результат 13.09")
    void testNextBlackFridays2024() {
        //arrange/act
        LocalDate result = Task2.getNextBlackFriday(LocalDate.of(2024, 8, 10));
        //assert
        assertThat(result).isEqualTo(LocalDate.of(2024, 9, 13));
    }
}
