package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task8Test {
    @Test
    @DisplayName("Нечетная строка")
    void oddString() {
        //act
        var result = Task8.isStringValidOdd("0101110");
        //assert
        assertThat(result).isTrue();
    }
    @Test
    @DisplayName("Четная строка")
    void evenString() {
        //act
        var result = Task8.isStringValidOdd("011110");
        //assert
        assertThat(result).isFalse();
    }


    @Test
    @DisplayName("Начинается с 0 - нечетная длина")
    void startWithNullOdd() {
        //act
        var result = Task8.isStringValidZeroOddOrOneEven("0101110");
        //assert
        assertThat(result).isTrue();
    }
    @Test
    @DisplayName("Начинается с 1 - четная длина")
    void startWithOneEven() {
        //act
        var result = Task8.isStringValidZeroOddOrOneEven("101110");
        //assert
        assertThat(result).isTrue();
    }
    @Test
    @DisplayName("Каждый нечетный символ - 1")
    void allOddIsOne() {
        //act
        var result = Task8.isStringValidAllOddOne("101110");
        //assert
        assertThat(result).isTrue();
    }
    @Test
    @DisplayName("Каждый нечетный - 1, неверно")
    void allOddIsOneNo() {
        //act
        var result = Task8.isStringValidAllOddOne("100110");
        //assert
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Нет последовательных символов - 1")
    void noOneOne() {
        //act
        var result = Task8.isStringValidNoOneOne("101010");
        //assert
        assertThat(result).isTrue();
    }
    @Test
    @DisplayName("Есть последовательные символы - 1")
    void OneOne() {
        //act
        var result = Task8.isStringValidNoOneOne("100110");
        //assert
        assertThat(result).isFalse();
    }
    @Test
    @DisplayName("Любая строка, кроме 11, 111")
    void notThisString() {
        //act
        var result = Task8.isStringValidAnyStringNotOnes("111111");
        //assert
        assertThat(result).isTrue();
    }
    @Test
    @DisplayName("Строка 11")
    void badString() {
        //act
        var result = Task8.isStringValidAnyStringNotOnes("11");
        //assert
        assertThat(result).isFalse();
    }
    @Test
    @DisplayName("Строка 111")
    void badStringMore() {
        //act
        var result = Task8.isStringValidAnyStringNotOnes("111");
        //assert
        assertThat(result).isFalse();
    }

}
