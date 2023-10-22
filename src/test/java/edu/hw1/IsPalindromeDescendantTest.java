package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class IsPalindromeDescendantTest {
    @Test
    @DisplayName("Палиндром, который не сходится")
    void isPalindromeDescendant_False() {
        //arrange
        int palindrome = 123456789;

        //act
        boolean res = Task5.isPalindromeDescendant(palindrome);

        //assert
        assertThat(res).isFalse();
    }

    @Test
    @DisplayName("Сходящийся палиндром")
    void isPalindromeDescendant_True() {
        //arrange
        int palindrome = 23336014;

        //act
        boolean res = Task5.isPalindromeDescendant(palindrome);

        //assert
        assertThat(res).isTrue();
    }
}
