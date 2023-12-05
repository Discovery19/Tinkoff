package edu.hw9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {
    @Test
    @DisplayName("Default test")
    void defaultTest(){
        //arrange
        Task1 task1 = new Task1();
        //act
        task1.push("metric_name1", new double[] {0.1, 0.05, 1.4, 5.1, 0.3});
        String result = task1.statistic();
        //assert
        assertThat(result).isEqualTo("metric_name1 сумма: 6.95 среднее: 1.39 максимум: 5.1 минимум: 0.05\n");
    }
    @Test
    @DisplayName("two Metrics test")
    void twoMetricsTest(){
        //arrange
        Task1 task1 = new Task1();
        //act
        task1.push("metric_name1", new double[] {0.1, 0.05, 1.4, 5.1, 0.3});
        task1.push("metric_name2", new double[] {0.1, 10.05, 1.4, 5.1, 0.3});
        String result = task1.statistic();
        //assert
        assertThat(result).isEqualTo("metric_name2 сумма: 16.95 среднее: 3.39 максимум: 10.05 минимум: 0.1\n" +
            "metric_name1 сумма: 6.95 среднее: 1.39 максимум: 5.1 минимум: 0.05\n");
    }
}
