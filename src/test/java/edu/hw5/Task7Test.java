package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task7Test {
    @Test
    @DisplayName("Первое состояние, подходящая строка")
    void firstGood() {
        //act
        var result = Task7.isStringValidFirstCondition("0101110");
        //assert
        assertThat(result).isTrue();
    }
    @Test
    @DisplayName("Первое состояние, неподходящая строка")
    void firstBad() {
        //act
        var result = Task7.isStringValidFirstCondition("0111110");
        //assert
        assertThat(result).isFalse();
    }


    @Test
    @DisplayName("Второе состояние, подходящая строка")
    void secondGood() {
        //act
        var result = Task7.isStringValidSecondCondition("100011001");
        //assert
        assertThat(result).isTrue();
    }
    @Test
    @DisplayName("Второе состояние, неподходящая строка")
    void secondBad() {
        //act
        var result = Task7.isStringValidSecondCondition("0111111");
        //assert
        assertThat(result).isFalse();
    }


    @Test
    @DisplayName("Третье состояние, подходящая строка")
    void thirdGood() {
        //act
        var result = Task7.isStringValidThirdCondition("01");
        //assert
        assertThat(result).isTrue();
    }
    @Test
    @DisplayName("Третье состояние, неподходящая строка")
    void thirdBad() {
        //act
        var result = Task7.isStringValidThirdCondition("");
        //assert
        assertThat(result).isFalse();
    }
}
