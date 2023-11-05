package edu.hw5;

import edu.hw5.task3.Task3;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {
    @Test
    @DisplayName("Дата сегодня")
    void dataParserToday() {
        //arrange
        Task3 task3 = new Task3();
        //act
        Optional<LocalDate> result = task3.parseDate("today");
        //assert
        assertThat(result).isEqualTo(Optional.of(LocalDate.now()));
    }
    @Test
    @DisplayName("Дата завтра")
    void dataParserTomorrow() {
        //arrange
        Task3 task3 = new Task3();
        //act
        Optional<LocalDate> result = task3.parseDate("tomorrow");
        //assert
        assertThat(result).isEqualTo(Optional.of(LocalDate.now().plusDays(1)));
    }
    @Test
    @DisplayName("Дата вчера")
    void dataParserYesterday() {
        //arrange
        Task3 task3 = new Task3();
        //act
        Optional<LocalDate> result = task3.parseDate("yesterday");
        //assert
        assertThat(result).isEqualTo(Optional.of(LocalDate.now().minusDays(1)));
    }
    @Test
    @DisplayName("День назад")
    void dataParserDayAgo() {
        //arrange
        Task3 task3 = new Task3();
        //act
        Optional<LocalDate> result = task3.parseDate("day ago");
        //assert
        assertThat(result).isEqualTo(Optional.of(LocalDate.now().minusDays(1)));
    }

    @Test
    @DisplayName("X дней назад")
    void dataParserXDaysAgo() {
        //arrange
        Task3 task3 = new Task3();
        //act
        Optional<LocalDate> result = task3.parseDate("10 days ago");
        //assert
        assertThat(result).isEqualTo(Optional.of(LocalDate.now().minusDays(10)));
    }
    @Test
    @DisplayName("2020-10-10")
    void dataParserDefault() {
        //arrange
        Task3 task3 = new Task3();
        //act
        Optional<LocalDate> result = task3.parseDate("2020-10-10");
        //assert
        assertThat(result).isEqualTo(Optional.of(LocalDate.of(2020,10,10)));
    }
    @Test
    @DisplayName("2020-12-2")
    void dataParserDefaultBadDay() {
        //arrange
        Task3 task3 = new Task3();
        //act
        Optional<LocalDate> result = task3.parseDate("2020-12-2");
        //assert
        assertThat(result).isEqualTo(Optional.of(LocalDate.of(2020,12,2)));
    }
    @Test
    @DisplayName("1/3/1976")
    void dataParserNotDefault() {
        //arrange
        Task3 task3 = new Task3();
        //act
        Optional<LocalDate> result = task3.parseDate("1/3/1976");
        //assert
        assertThat(result).isEqualTo(Optional.of(LocalDate.of(1976,3,1)));
    }
    @Test
    @DisplayName("1/3/20")
    void dataParserNotDefaultVeryBad() {
        //arrange
        Task3 task3 = new Task3();
        //act
        Optional<LocalDate> result = task3.parseDate("1/3/20");
        //assert
        assertThat(result).isEqualTo(Optional.of(LocalDate.of(2020,3,1)));
    }
}

