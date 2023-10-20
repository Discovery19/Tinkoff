package edu.hw3;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {
    @Test
    @DisplayName("Словарь чисел")
    void freqDictInteger() {
        //arrange
        List<Integer> list = List.of(1, 1, 1, 11, 15);
        //act
        String res = Task3.freqDict(list);
        //assert
        assertThat(res).isEqualTo("1: 3\n11: 1\n15: 1\n");
    }

    @Test
    @DisplayName("Словарь слов латиница")
    void freqDictString() {
        //arrange
        List<String> list = List.of("this", "and", "that", "and");
        //act
        String res = Task3.freqDict(list);
        //assert
        assertThat(res).isEqualTo("that: 1\nand: 2\nthis: 1\n");
    }

    @Test
    @DisplayName("Словарь слов латиница + русские слова")
    void freqDictStringRus() {
        //arrange
        List<String> list = List.of("русское", "and", "that", "and");
        //act
        String res = Task3.freqDict(list);
        //assert
        assertThat(res).isEqualTo("that: 1\nand: 2\nрусское: 1\n");
    }
}
