package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FixStringTest {
    @Test
    @DisplayName("Проверка четной строки")
    void fixString_EvenNumber() {
        //arrange
        String stringEven = "hTsii  s aimex dpus rtni.g";
        //act
        String res = Task4.fixString(stringEven);
        //assert
        assertThat(res).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("Проверка нечетной строки")
    void fixString_OddNumber() {
        //arrange
        String stringOdd = "badce";
        //act
        String res = Task4.fixString(stringOdd);
        //assert
        assertThat(res).isEqualTo("abcde");
    }
}
