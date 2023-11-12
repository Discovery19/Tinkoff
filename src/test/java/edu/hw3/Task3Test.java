package edu.hw3;

import java.util.List;
import java.util.Map;
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
        var res = Task3.freqDict(list);
        //assert
        assertThat(res).isEqualTo(Map.of(1, 3, 11, 1, 15, 1));
    }

    @Test
    @DisplayName("Словарь слов латиница")
    void freqDictString() {
        //arrange
        List<String> list = List.of("this", "and", "that", "and");
        //act
        var res = Task3.freqDict(list);
        //assert
        assertThat(res).isEqualTo(Map.of("that", 1, "and", 2, "this", 1));
    }

    @Test
    @DisplayName("Словарь слов латиница + русские слова")
    void freqDictStringRus() {
        //arrange
        List<String> list = List.of("русское", "and", "that", "and");
        //act
        var res = Task3.freqDict(list);
        //assert
        assertThat(res).isEqualTo(Map.of("that", 1, "and", 2, "русское", 1));
    }
}
