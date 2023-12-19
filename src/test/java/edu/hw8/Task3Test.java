package edu.hw8;

import edu.hw8.Task3.Task3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {
    @Test
    @DisplayName("Тест подбор пароля")
    void test() {
        Task3 task3 = new Task3(4, Map.of("user5", "12s5"));
        task3.multiThreadNextPassword();
        assertThat(task3.getResult()).containsEntry("user5", "12s5");
    }
    @Test
    @DisplayName("Тест подбор 2 пароля")
    void testTwoPasswords() {
        Task3 task3 = new Task3(4, Map.of("user5", "12s5", "user4", "pn4"));
        task3.multiThreadNextPassword();
        assertThat(task3.getResult()).containsEntry("user5", "12s5");
        assertThat(task3.getResult()).containsEntry("user4", "pn4");
    }
}
