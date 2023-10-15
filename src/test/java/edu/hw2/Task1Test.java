package edu.hw2;

import edu.hw2.Task1.Expr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task1Test {
    static Arguments[] constants() {
        return new Arguments[] {
            Arguments.of(new Expr.Constant(10), new Expr.Negate(new Expr.Constant(40))),
            Arguments.of(new Expr.Negate(new Expr.Constant(40)), new Expr.Constant(10))
        };
    }
    @ParameterizedTest
    @MethodSource("constants")
    @DisplayName("Параметризованный тест сумма двух чисел с разными знаками")
    void standardAddition(Expr a, Expr b) {
        assertThat(new Expr.Addition(a, b).evaluate()).isEqualTo(-30);
    }
    @Test
    @DisplayName("Стандартное умножение простых чисел")
    void standardMultiplication(){
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var multi= new Expr.Multiplication(two,four);
        assertThat(multi.evaluate()).isEqualTo(8);
    }
    @Test
    @DisplayName("Стандартное возведение в степень")
    void standardExponent(){
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var multi= new Expr.Exponent(two,four);
        assertThat(multi.evaluate()).isEqualTo(16);
    }
}
