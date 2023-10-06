package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SampleTest {
//    @Test
//    @DisplayName("Фильтрация четных чисел")
//    void filterEvenNumbers() {
//        // given
//        int[] numbers = new int[] {1, 2, 3, 4, 5};
//
//        // when
//        int[] evenNumbers = EvenArrayUtils.filter(numbers);
//
//        // then
//        assertThat(evenNumbers)
//            .containsExactly(2, 4)
//            .hasSize(2);
//    }
    @Test
    @DisplayName("1. Длина видео")
    void minutesToSeconds1(){
        String string="01:00";
        int res=Main.minutesToSeconds(string);
        assertThat(res).isEqualTo(60);
    }
    @Test
    @DisplayName("2. Длина видео")
    void minutesToSeconds2(){
        String string="00:00";
        int res=Main.minutesToSeconds(string);
        assertThat(res).isEqualTo(0);
    }
    @Test
    @DisplayName("3. Длина видео")
    void minutesToSeconds3(){
        String string="16:60";
        int res=Main.minutesToSeconds(string);
        assertThat(res).isEqualTo(-1);
    }
    @Test
    @DisplayName("1. Количество цифр")
    void countDigits1(){
        int res=Main.countDigits(4666);
        assertThat(res).isEqualTo(4);
    }
    @Test
    @DisplayName("2. Количество цифр")
    void countDigits2(){
        int res=Main.countDigits(0);
        assertThat(res).isEqualTo(1);
    }

    @Test
    @DisplayName("1. Вложенный массив")
    void isNestable1(){
        boolean res=Main.isNestable(new int[] {9, 9, 8}, new int[] {8, 9});
        assertThat(res).isEqualTo(false);
    }
    @Test
    @DisplayName("2. Вложенный массив")
    void isNestable2(){
        boolean res=Main.isNestable(new int []{1, 2, 3, 4}, new int[]{0, 6});
        assertThat(res).isEqualTo(true);
    }
    @Test
    @DisplayName("1. Сломанная строка")
    void fixString1(){
        String res=Main.fixString("hTsii  s aimex dpus rtni.g");
        assertThat(res).isEqualTo("This is a mixed up string.");
    }
    @Test
    @DisplayName("2. Сломанная строка")
    void fixString2(){
        String res=Main.fixString("badce");
        assertThat(res).isEqualTo("abcde");
    }
    @Test
    @DisplayName("1. Особый палиндром")
    void isPalindromeDescendant1(){
        boolean res=Main.isPalindromeDescendant(123657432);
        assertThat(res).isEqualTo(false);
    }
    @Test
    @DisplayName("2. Особый палиндром")
    void isPalindromeDescendant2(){
        boolean res=Main.isPalindromeDescendant(23336014);
        assertThat(res).isEqualTo(true);
    }
    @Test
    @DisplayName("1. Циклический битовый сдвиг")
    void rotateLeft(){
        int res=Main.rotateRight(8, 1);
        assertThat(res).isEqualTo(4 );
    }
    @Test
    @DisplayName("2. Циклический битовый сдвиг")
    void rotateRight(){
        int res=Main.rotateLeft(16, 1);
        assertThat(res).isEqualTo(1);
    }
    @Test
    @DisplayName("1. Кони на доске")
    void knightBoardCapture1(){
        boolean res=Main.knightBoardCapture(new int[][]{
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}}
        );
        assertThat(res).isEqualTo(true);
    }
    @Test
    @DisplayName("2. Кони на доске")
    void knightBoardCapture2(){
        boolean res=Main.knightBoardCapture(new int[][]{
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1}
        });
        assertThat(res).isEqualTo(false);
    }
}
