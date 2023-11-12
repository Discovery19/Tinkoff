package edu.hw3;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {
    @Test
    @DisplayName("Простая кластеризация, пустая строка")
    void clusterizeEmpty() {
        //arrange
        String string = "";
        //act
        List<String> result = Task2.clusterize(string);
        //assert
        assertThat(result).isEqualTo(List.of());
    }
    @Test
    @DisplayName("Простая кластеризация, разделение на три группы")
    void clusterizeEasy() {
        //arrange
        String string = "()()()";
        //act
        List<String> result = Task2.clusterize(string);
        //assert
        assertThat(result).isEqualTo(List.of("()", "()", "()"));
    }

    @Test
    @DisplayName("Кластеризация, одна группа")
    void clusterizeOneGroup() {
        //arrange
        String string = "((()))";
        //act
        List<String> result = Task2.clusterize(string);
        //assert
        assertThat(result).isEqualTo(List.of("((()))"));
    }

    @Test
    @DisplayName("Сложная кластеризация")
    void clusterizeDifficult() {
        //arrange
        String string = "((()))(())()()(()())";
        //act
        List<String> result = Task2.clusterize(string);
        //assert
        assertThat(result).isEqualTo(List.of("((()))", "(())", "()", "()", "(()())"));
    }

    @Test
    @DisplayName("Сложная кластеризация, вложенная")
    void clusterizeDifficultIn() {
        //arrange
        String string = "((())())(()(()()))";
        //act
        List<String> result = Task2.clusterize(string);
        //assert
        assertThat(result).isEqualTo(List.of("((())())", "(()(()()))"));
    }
}
