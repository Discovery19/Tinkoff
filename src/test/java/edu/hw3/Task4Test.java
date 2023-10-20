package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {
    @Test
    @DisplayName("римская 2")
    void convertToRomanOne() {
        //arrange
        int num = 2;
        //act
        String res = Task4.convertToRoman(num);
        //assert
        assertThat(res).isEqualTo("II");
    }

    @Test
    @DisplayName("римская 16")
    void convertToRomanSixteen() {
        //arrange
        int num = 16;
        //act
        String res = Task4.convertToRoman(num);
        //assert
        assertThat(res).isEqualTo("XVI");
    }

    @Test
    @DisplayName("римская 1669")
    void convertToRomanBigNumb() {
        //arrange
        int num = 1669;
        //act
        String res = Task4.convertToRoman(num);
        //assert
        assertThat(res).isEqualTo("MDCLXIX");
    }
}
