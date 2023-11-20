package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {
    @Test
    @DisplayName("Подходящий пароль")
    void passwordGood() {
        //act
        var result = Task4.isStringMatchRegex("1234@5678");
        //assert
        assertThat(result).isTrue();
    }
    @Test
    @DisplayName("Неподходящий пароль")
    void passwordBad() {
        //act
        var result = Task4.isStringMatchRegex("12345678");
        //assert
        assertThat(result).isFalse();
    }
    @Test
    @DisplayName("Подходящий пароль, несколько нужных символов")
    void passwordBadMore() {
        //act
        var result = Task4.isStringMatchRegex("^1$23456$78");
        //assert
        assertThat(result).isTrue();
    }
}
