package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RotateRightLeftTest {
    @Test
    @DisplayName("Стандартный сдвиг вправо на 1")
    void rotateRight_Four() {
        //arrange
        int number = 8;
        int rotateNum = 1;
        //act
        int res = Task7.rotateRight(number, rotateNum);
        //assert
        assertThat(res).isEqualTo(4);
    }

    @Test
    @DisplayName("Стандартный сдвиг влево на 2")
    void rotateLeft_Two() {
        //arrange
        int number = 16;
        int rotateNum = 2;
        //act
        int res = Task7.rotateLeft(number, rotateNum);
        //assert
        assertThat(res).isEqualTo(2);
    }

    @Test
    @DisplayName("Сдвиг единицы влево на 1")
    void rotateLeft_NoRotate() {
        //arrange
        int number = 1;
        int rotateNum = 1;
        //act
        int res = Task7.rotateLeft(number, rotateNum);
        //assert
        assertThat(res).isEqualTo(1);
    }

    @Test
    @DisplayName("Сдвиг единицы вправо на 1")
    void rotateRight_NoRotate() {
        //arrange
        int number = 1;
        int rotateNum = 1;
        //act
        int res = Task7.rotateRight(number, rotateNum);
        //assert
        assertThat(res).isEqualTo(1);
    }

    @Test
    @DisplayName("Отрицательный сдвиг")
    void rotateRight_MinusOneRotate() {
        //arrange
        int number = 10;
        int rotateNum = -1;
        //act
        int res = Task7.rotateRight(number, rotateNum);
        //assert
        assertThat(res).isEqualTo(10);
    }
}
