package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task5Test {
    @Test
    @DisplayName("Подходящий номер")
    void carGood() {
        //act
        var result = Task5.isStringMatchRegex("А123ВЕ777");
        //assert
        assertThat(result).isTrue();
    }
    @Test
    @DisplayName("Неподходящий номер")
    void carBad() {
        //act
        var result = Task5.isStringMatchRegex("123АВЕ777");
        //assert
        assertThat(result).isFalse();
    }
    @Test
    @DisplayName("Неподходящий номер, в задании написано, что неподходящий, но в жизни вполне реальный")
    void carBadStrange() {
        //act
        var result = Task5.isStringMatchRegex("А123ВГ77");
        //assert
        assertThat(result).isFalse();
    }
    @Test
    @DisplayName("Неподходящий пароль")
    void carBadMore() {
        //act
        var result = Task5.isStringMatchRegex("А123ВЕ7777");
        //assert
        assertThat(result).isFalse();
    }
}
