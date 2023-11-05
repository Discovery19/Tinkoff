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
    @DisplayName("Неподходящий пароль, несколько нужных символов, а не один")
    void passwordBadMore() {
        //act
        var result = Task4.isStringMatchRegex("^123456$78");
        //assert
        assertThat(result).isFalse();
    }
}
