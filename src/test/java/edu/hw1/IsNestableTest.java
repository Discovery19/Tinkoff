package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class IsNestableTest {
    @Test
    @DisplayName("Стандартный массив, неверный") void isNestable_False() {
        boolean res = Task3.isNestable(new int[] {9, 9, 8}, new int[] {8, 9});
        assertThat(res).isFalse();
    }

    @Test
    @DisplayName("Стандартный массив, верный") void isNestable_True() {
        boolean res = Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {0, 6});
        assertThat(res).isTrue();
    }

    @Test
    @DisplayName("Пустой первый массив") void isNestable_ZeroArray_False() {
        boolean res = Task3.isNestable(new int[] {}, new int[] {0, 6});
        assertThat(res).isFalse();
    }

    @Test
    @DisplayName("Пустой второй массив") void isNestable_AnotherZeroArray_False() {
        boolean res = Task3.isNestable(new int[] {0, 6}, new int[] {});
        assertThat(res).isFalse();
    }

    @Test
    @DisplayName("Два пустых массива") void isNestable_TwoZeroArrays_False() {
        boolean res = Task3.isNestable(new int[] {}, new int[] {});
        assertThat(res).isFalse();
    }
}
