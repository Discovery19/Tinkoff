package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.BIRD;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.FISH;
import static org.assertj.core.api.Assertions.assertThat;

class Task12Test {
    @Test
    @DisplayName("Проверка вес больше чем рост")
    void weightStandard() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.addAnimal(new Animal("Bird", BIRD, F, 12, 120, 160, false));
        zoo.addAnimal(new Animal("Black dog", DOG, M, 10, 130, 65, true));
        zoo.addAnimal(new Animal("Just cat", CAT, M, 4, 80, 30, true));
        zoo.addAnimal(new Animal("Just fish", FISH, M, 8, 80, 30, false));
        //act
        var animals = zoo.weightMoreThanHeight();
        //assert
        assertThat(animals)
            .isOne();
    }

    @Test
    @DisplayName("Нет животных")
    void weightNoAnimals() {
        //arrange
        Zoo zoo = new Zoo();
        //act
        var animals = zoo.weightMoreThanHeight();
        //assert
        assertThat(animals).isZero();
    }
}
