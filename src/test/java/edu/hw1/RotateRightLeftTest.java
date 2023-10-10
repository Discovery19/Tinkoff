package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RotateRightLeftTest {
    @Test
    @DisplayName("1. Циклический битовый сдвиг") void rotateRight_Four() {
        int res = Task7.rotateRight(8, 1);
        assertThat(res).isEqualTo(4);
    }

    @Test
    @DisplayName("2. Циклический битовый сдвиг") void rotateLeft_One() {
        int res = Task7.rotateLeft(16, 1);
        assertThat(res).isEqualTo(1);
    }
    @Test
    @DisplayName("3. Циклический битовый сдвиг") void rotateLeft_NoRotate() {
        int res = Task7.rotateLeft(1, 1);
        assertThat(res).isEqualTo(1);
    }
    @Test
    @DisplayName("4. Циклический битовый сдвиг") void rotateRight_NoRotate() {
        int res = Task7.rotateRight(1, 1);
        assertThat(res).isEqualTo(1);
    }
    @Test
    @DisplayName("5. Циклический битовый сдвиг") void rotateRight_MinusOneRotate() {
        int res = Task7.rotateRight(10, -1);
        assertThat(res).isEqualTo(10);
    }
}
