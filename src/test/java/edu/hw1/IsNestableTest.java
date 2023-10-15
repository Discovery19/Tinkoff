package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class IsNestableTest {
    @Test
    @DisplayName("Стандартный массив, неверный")
    void isNestable_False() {
        //arrange
        int[] first_array = new int[] {9, 9, 8};
        int[] second_array = new int[] {8, 9};

        //act
        boolean res = Task3.isNestable(first_array, second_array);

        //assert
        assertThat(res).isFalse();
    }

    @Test
    @DisplayName("Стандартный массив, верный")
    void isNestable_True() {
        //arrange
        int[] first_array = new int[] {1, 2, 3, 4};
        int[] second_array = new int[] {0, 6};

        //act
        boolean res = Task3.isNestable(first_array, second_array);

        //assert
        assertThat(res).isTrue();
    }

    @Test
    @DisplayName("Пустой первый массив")
    void isNestable_ZeroArray_False() {
        //arrange
        int[] first_array = new int[] {};
        int[] second_array = new int[] {0, 6};

        //act
        boolean res = Task3.isNestable(first_array, second_array);

        //assert
        assertThat(res).isFalse();
    }

    @Test
    @DisplayName("Пустой второй массив")
    void isNestable_AnotherZeroArray_False() {
        //arrange
        int[] first_array = new int[] {0, 6};
        int[] second_array = new int[] {};

        //act
        boolean res = Task3.isNestable(first_array, second_array);

        //assert
        assertThat(res).isFalse();
    }

    @Test
    @DisplayName("Два пустых массива")
    void isNestable_TwoZeroArrays_False() {
        //arrange
        int[] first_array = new int[] {};
        int[] second_array = new int[] {};

        //act
        boolean res = Task3.isNestable(first_array, second_array);

        //assert
        assertThat(res).isFalse();
    }
}
