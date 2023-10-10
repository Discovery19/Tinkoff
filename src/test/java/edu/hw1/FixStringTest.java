package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FixStringTest {
    @Test
    @DisplayName("1. Сломанная строка") void fixString_EvenNumber() {
        String res = Task4.fixString("hTsii  s aimex dpus rtni.g");
        assertThat(res).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("2. Сломанная строка") void fixString_OddNumber() {
        String res = Task4.fixString("badce");
        assertThat(res).isEqualTo("abcde");
    }
}
