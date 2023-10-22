package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CountDigitsTest {
    @Test @DisplayName("Стандартный просчет 4-ех значного числа")
    void countDigitsShouldConvertFour() {
        //arrange
        int fourRangeNumber = 4666;

        //act
        int res = Task2.countDigits(fourRangeNumber);

        //assert
        assertThat(res).isEqualTo(4);
    }

    @Test @DisplayName("Проверка работы с нулем")
    void countDigitsShouldConvertOne() {
        //arrange
        int zero = 0;

        //act
        int res = Task2.countDigits(zero);

        //assert
        assertThat(res).isEqualTo(1);
    }
}
