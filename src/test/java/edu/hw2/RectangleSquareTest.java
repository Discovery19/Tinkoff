package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RectangleSquareTest {
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("Тест на LSP")
    void rectangleOrSquareArea(Rectangle rect) {
        rect.setWidth(20);
        rect.setHeight(10);

        assertThat(rect.getArea()).isEqualTo(200);
    }
    @Test
    @DisplayName("Тест на площадь прямогульника")
    void rectangleArea(){
        Rectangle rect = new Rectangle();
        rect.setWidth(30);
        rect.setHeight(10);
        assertThat(rect.getArea()).isEqualTo(300);
    }
    @Test
    @DisplayName("Тест на площадь квадрата")
    void squareArea(){
        Square rect = new Square();
        rect.setWidth(30);
        rect.setHeight(30);
        assertThat(rect.getArea()).isEqualTo(900);
    }
}
