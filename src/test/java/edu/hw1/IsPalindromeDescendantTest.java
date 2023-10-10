package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class IsPalindromeDescendantTest {
    @Test
    @DisplayName("1. Особый палиндром") void isPalindromeDescendant_False() {
        boolean res = Task5.isPalindromeDescendant(123456789);
        assertThat(res).isFalse();
    }

    @Test
    @DisplayName("2. Особый палиндром") void isPalindromeDescendant_True() {
        boolean res = Task5.isPalindromeDescendant(23336014);
        assertThat(res).isTrue();
    }
}
