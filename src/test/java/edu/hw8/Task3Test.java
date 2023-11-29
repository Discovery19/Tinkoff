//package edu.hw8;
//
//import edu.hw8.Task3.Task3;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import java.util.Collection;
//import java.util.List;
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class Task3Test {
//    @Test
//    @DisplayName("Тест подбор 10 паролей")
//    void test() throws InterruptedException {
//        Task3 task3 = new Task3();
//        task3.multiThreadNextPassword();
//        Thread.currentThread().wait(10000);
//        assertThat(task3.getResult().size()).isEqualTo(10);
//    }
//
//    @Test
//    @DisplayName("Сверка hashCode")
//    void testHashCode() {
//        Task3 task3 = new Task3();
//        task3.multiThreadNextPassword();
//        Collection<String> list = task3.getPasswordDataBase().values();
//        int hashCode = task3.getResult().get("user5").hashCode();
//        boolean result = false;
//        for (String s : list) {
//            if (s.hashCode() == hashCode) {
//                result = true;
//                break;
//            }
//        }
//        assertThat(result).isTrue();
//    }
//}
