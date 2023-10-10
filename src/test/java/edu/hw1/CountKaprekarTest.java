package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CountKaprekarTest {
    @Test
    @DisplayName("1. Постоянная Капрекара") void countK_Three() {
        int res = Task6.countK(1234, 0);
        assertThat(res).isEqualTo(3);
    }

    @Test
    @DisplayName("2. Постоянная Капрекара") void countK_Five() {
        int res = Task6.countK(6621, 0);
        assertThat(res).isEqualTo(5);
    }
    @Test
    @DisplayName("3. Постоянная Капрекара") void countK_Thousand_MinusOne() {
        int res = Task6.countK(1000, 0);
        assertThat(res).isEqualTo(-1);
    }
    @Test
    @DisplayName("4. Постоянная Капрекара") void countK_OneNumber_MinusOne() {
        int res = Task6.countK(1111, 0);
        assertThat(res).isEqualTo(-1);
    }
    @Test
    @DisplayName("5. Постоянная Капрекара") void countK_TenThousand_MinusOne() {
        int res = Task6.countK(10000, 0);
        assertThat(res).isEqualTo(-1);
    }
}
