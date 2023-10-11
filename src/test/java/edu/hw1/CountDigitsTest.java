package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CountDigitsTest {
    @Test
    @DisplayName("Стандартный просчет 4-ех значного числа")
    void countDigitsShouldConvertFour() {
        int res = Task2.countDigits(4666);
        assertThat(res).isEqualTo(4);
    }

    @Test
    @DisplayName("Проверка работы с нулем")
    void countDigitsShouldConvertOne() {
        int res = Task2.countDigits(0);
        assertThat(res).isEqualTo(1);
    }
}
