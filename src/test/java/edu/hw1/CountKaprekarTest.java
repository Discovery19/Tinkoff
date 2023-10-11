package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CountKaprekarTest {
    @Test
    @DisplayName("Стандартное 4-ех значное число") void countK_Three() {
        int res = Task6.countK(1234, 0);
        assertThat(res).isEqualTo(3);
    }

    @Test
    @DisplayName("Стандартное 4-ех значное число") void countK_Five() {
        int res = Task6.countK(6621, 0);
        assertThat(res).isEqualTo(5);
    }

    @Test
    @DisplayName("Проверка элементов меньше допустимого диапазона значений (1000)") void countK_Thousand_MinusOne() {
        int res = Task6.countK(1000, 0);
        assertThat(res).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка числа, в котором все цифры равны") void countK_OneNumber_MinusOne() {
        int res = Task6.countK(1111, 0);
        assertThat(res).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка числа больше допустимого диапазона") void countK_TenThousand_MinusOne() {
        int res = Task6.countK(10000, 0);
        assertThat(res).isEqualTo(-1);
    }
}
