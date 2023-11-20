package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {
    @Test
    @DisplayName("Один час")
    void testCountTime() {
        //act
        String result = Task1.countTime(List.of("2020-01-01, 10:00 - 2020-01-01, 11:00"
            ,"2022-04-01, 21:30 - 2022-04-01, 22:30"));
        //assert
        assertThat(result).isEqualTo("1ч 0м");
    }
    @Test
    @DisplayName("Тест из задания")
    void testCountTimeAnotherDay() {
        //act
        String result = Task1.countTime(List.of("2022-03-12, 20:20 - 2022-03-12, 23:50",
        "2022-04-01, 21:30 - 2022-04-02, 01:20"));
        //assert
        assertThat(result).isEqualTo("3ч 40м");
    }
    @Test
    @DisplayName("Тест с большим значениями")
    void testCountTimeWeek() {
        //act
        String result = Task1.countTime(List.of("2022-01-01, 10:00 - 2022-01-08, 10:00"
            ,"2022-01-01, 10:00 - 2022-01-01, 11:00"));
        //assert
        assertThat(result).isEqualTo("84ч 30м");
    }
}
