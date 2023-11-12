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
<<<<<<< HEAD
<<<<<<< HEAD
        int nul = 0;
=======
        int zero = 0;
>>>>>>> 9447ce8676dcbbacfbd167d3aa652b59ae179b2c
=======
        int zero = 0;
>>>>>>> c3ba2c90c553a3724fd3db96fbeb6ac8a442a5fc

        //act
        int res = Task2.countDigits(zero);

        //assert
        assertThat(res).isEqualTo(1);
    }
}
