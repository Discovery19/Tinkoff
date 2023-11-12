package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {
    @ParameterizedTest
    @CsvSource({
        "2, II",
        "16, XVI",
        "1669, MDCLXIX"
    })
    @DisplayName("Convert to Roman")
    void convertToRoman(int num, String expected) {
        //arrange//act
        String res = new Task4().convertToRoman(num);
        //assert
        assertThat(res).isEqualTo(expected);
    }

}
