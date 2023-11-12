package edu.hw3;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task8Test {
    @Test
    @DisplayName("Итерация в обратную сторону Set")
    void backIterationSet() {
        //arrange
        Set<Integer> set = Set.of(1, 2, 3, 4, 5);
        StringBuilder sb = new StringBuilder();
        Task8.BackIterator itr = new Task8.BackIterator(set);
        //act
        while (itr.hasNext()) {
            sb.append(itr.next()).append(" ");
        }
        //assert
        assertThat(sb.toString()).isEqualTo("5 4 3 2 1 ");
    }

    @Test
    @DisplayName("Итерация в обртаную сторону List")
    void backIterationList() {
        //arrange
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        StringBuilder sb = new StringBuilder();
        Task8.BackIterator itr = new Task8.BackIterator(list);
        //act
        while (itr.hasNext()) {
            sb.append(itr.next()).append(" ");
        }
        //assert
        assertThat(sb.toString()).isEqualTo("5 4 3 2 1 ");
    }

    @Test
    @DisplayName("Итерация в обртаную сторону пустой массив")
    void backIterationNull() {
        //arrange
        List<Integer> list = List.of();
        StringBuilder sb = new StringBuilder();
        Task8.BackIterator itr = new Task8.BackIterator(list);
        //act
        while (itr.hasNext()) {
            sb.append(itr.next()).append(" ");
        }
        //assert
        assertThat(sb.toString()).isEmpty();
    }
}
