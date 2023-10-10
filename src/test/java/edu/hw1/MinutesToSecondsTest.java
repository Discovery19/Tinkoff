package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MinutesToSecondsTest {
    @Test
    @DisplayName("1. Длина видео") void minutesToSeconds_Sixty() {
        String string = "01:00";
        int res = Task1.minutesToSeconds(string);
        assertThat(res).isEqualTo(60);
    }

    @Test
    @DisplayName("2. Длина видео") void minutesToSeconds_Zero() {
        String string = "00:00";
        int res = Task1.minutesToSeconds(string);
        assertThat(res).isZero();
    }

    @Test @DisplayName("3. Длина видео") void minutesToSeconds_MoreSixtySec() {
        String string = "16:60";
        int res = Task1.minutesToSeconds(string);
        assertThat(res).isEqualTo(-1);
    }
    @Test @DisplayName("4. Длина видео") void minutesToSeconds_BadString() {
        String string = "16M:50S";
        int res = Task1.minutesToSeconds(string);
        assertThat(res).isEqualTo(-1);
    }
}
