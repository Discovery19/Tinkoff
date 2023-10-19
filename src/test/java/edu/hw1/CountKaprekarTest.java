package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CountKaprekarTest {
    @Test
    @DisplayName("Стандартное 4-ех значное число")
    void countK_Three() {
        //arrange
        int fourRangeNum = 1234;
        //act
        int res = Task6.countK(fourRangeNum, 0);

        //act
        int res = Task6.countK(fourRangeNum, 0);

        //assert
        assertThat(res).isEqualTo(3);
    }

    @Test
    @DisplayName("Стандартное 4-ех значное число")
    void countK_Five() {
        //arrange
        int fourRangeNum = 6621;
        //act
        int res = Task6.countK(fourRangeNum, 0);

        //act
        int res = Task6.countK(fourRangeNum, 0);

        //assert
        assertThat(res).isEqualTo(5);
    }

    @Test
    @DisplayName("Проверка элементов меньше допустимого диапазона значений (1000)")
    void countK_Thousand_MinusOne() {
        //arrange
        int fourRangeNum = 1000;
        //act
        int res = Task6.countK(fourRangeNum, 0);

        //act
        int res = Task6.countK(fourRangeNum, 0);

        //assert
        assertThat(res).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка числа, в котором все цифры равны")
    void countK_OneNumber_MinusOne() {
        //arrange
        int fourOne = 1111;
        //act
        int res = Task6.countK(fourOne, 0);

        //act
        int res = Task6.countK(fourOne, 0);

        //assert
        assertThat(res).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка числа больше допустимого диапазона")
    void countK_TenThousand_MinusOne() {
        //arrange
        int fiveRangeNum = 10000;
        //act
        int res = Task6.countK(fiveRangeNum, 0);

        //act
        int res = Task6.countK(fiveRangeNum, 0);

        //assert
        assertThat(res).isEqualTo(-1);
    }
}
