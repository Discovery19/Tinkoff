package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

class Task7Test {
    @Test
    @DisplayName("Мап с нулевым ключом")
    void tree() {
        //arrange
        TreeMap<String, String> treeMap = new TreeMap<>(new Task7Comparator());
        //act
        treeMap.put(null, "value");
        treeMap.put("key", "value1");
        //assert
        assertThat(treeMap.containsKey(null)).isTrue();
    }
}
