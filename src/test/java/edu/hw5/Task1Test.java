package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {
    @Test
    @DisplayName("Один час")
    void testCountTime() {
        //act
        String result = Task1.countTime("2020-01-01 10:00 - 2020-01-01 11:00");
        //assert
        assertThat(result).isEqualTo("0 days 1 hours 0 minutes");
    }
    @Test
    @DisplayName("Переход на другой день")
    void testCountTimeAnotherDay() {
        //act
        String result = Task1.countTime("2022-04-01 21:30 - 2022-04-02 01:20");
        //assert
        assertThat(result).isEqualTo("0 days 3 hours 50 minutes");
    }
    @Test
    @DisplayName("Прошла ровно неделя")
    void testCountTimeWeek() {
        //act
        String result = Task1.countTime("2022-01-01 10:00 - 2022-01-08 10:00");
        //assert
        assertThat(result).isEqualTo("7 days 0 hours 0 minutes");
    }
}
