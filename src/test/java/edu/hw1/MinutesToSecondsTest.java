package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MinutesToSecondsTest {
    @Test
    @DisplayName("Длина видео в минуту")
    void minutesToSeconds_Sixty() {
        //arrange
        String string = "01:00";
        //act
        int res = Task1.minutesToSeconds(string);

        //act
        int res = Task1.minutesToSeconds(string);

        //assert
        assertThat(res).isEqualTo(60);
    }

    @Test
    @DisplayName("Видео без длины")
    void minutesToSeconds_Zero() {
        //arrange
        String string = "00:00";
        //act
        int res = Task1.minutesToSeconds(string);

        //act
        int res = Task1.minutesToSeconds(string);

        //assert
        assertThat(res).isZero();
    }

    @Test @DisplayName("Длина видео с секундами в недопустимом диапазоне")
    void minutesToSeconds_MoreSixtySec() {
        //arrange
        String string = "16:60";
        //act
        int res = Task1.minutesToSeconds(string);

        //act
        int res = Task1.minutesToSeconds(string);

        //assert
        assertThat(res).isEqualTo(-1);
    }

    @Test @DisplayName("Неверный ввод")
    void minutesToSeconds_BadString() {
        //arrange
        String string = "16M:50S";
        //act
        int res = Task1.minutesToSeconds(string);

        //act
        int res = Task1.minutesToSeconds(string);

        //assert
        assertThat(res).isEqualTo(-1);
    }
}
