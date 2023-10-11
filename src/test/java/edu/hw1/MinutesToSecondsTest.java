package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MinutesToSecondsTest {
    @Test
    @DisplayName("Длина видео в минуту") void minutesToSeconds_Sixty() {
        String string = "01:00";
        int res = Task1.minutesToSeconds(string);
        assertThat(res).isEqualTo(60);
    }

    @Test
    @DisplayName("Видео без длины") void minutesToSeconds_Zero() {
        String string = "00:00";
        int res = Task1.minutesToSeconds(string);
        assertThat(res).isZero();
    }

    @Test @DisplayName("Длина видео с секундами в недопустимом диапазоне") void minutesToSeconds_MoreSixtySec() {
        String string = "16:60";
        int res = Task1.minutesToSeconds(string);
        assertThat(res).isEqualTo(-1);
    }

    @Test @DisplayName("Неверный ввод") void minutesToSeconds_BadString() {
        String string = "16M:50S";
        int res = Task1.minutesToSeconds(string);
        assertThat(res).isEqualTo(-1);
    }
}
