package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task6Test {
    @Test
    @DisplayName("Подстрока")
    void substring() {
        //act
        var result = Task6.isStringIsSubstring(" achfdbaabgabcaabg" , "abc");
        //assert
        assertThat(result).isTrue();
    }
    @Test
    @DisplayName("Не подстрока")
    void notSubsting() {
        //act
        var result = Task6.isStringIsSubstring("achfdbaabgabcaabg", "123");
        //assert
        assertThat(result).isFalse();
    }
    @Test
    @DisplayName("Пробел")
    void notSubstringZero() {
        //act
        var result = Task6.isStringIsSubstring("achfdbaabgabcaabg", " ");
        //assert
        assertThat(result).isFalse();
    }
}
