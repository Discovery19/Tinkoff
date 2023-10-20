package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.Task6;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task6Test {
    @Test
    @DisplayName("Выполняю все операции с рынком акций")
    void stockMarket() {
        //arrange
        Task6 market = new Task6();
        market.add(new Stock("ABC", 100));
        market.add(new Stock("123", 1230));
        market.add(new Stock("QWE", 1000));
        market.add(new Stock("ASD", 10));
        market.remove(new Stock("123", 1230));
        //act
        Stock res = market.mostValuableStock();
        //assert
        assertThat(res.getName()).isEqualTo("QWE");
        assertThat(res.getPrice()).isEqualTo(1000);
    }
}
