package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static org.assertj.core.api.Assertions.assertThat;

class Task5Test {
    @Test
    @DisplayName("Обычное кол-во полов максимальное")
    void longestNameStandard() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.addAnimal(new Animal("Dog", DOG, F, 12, 120, 60, true));
        zoo.addAnimal(new Animal("Black dog", DOG, M, 10, 130, 65, true));
        zoo.addAnimal(new Animal("Just cat", CAT, M, 8, 80, 30, true));
        //act
        Animal.Sex sex = zoo.whatSexAmountMore();
        //assert
        assertThat(sex).isEqualTo(M);
    }

    @Test
    @DisplayName("Одинаковое кол-во животных разных полов, возврат F")
    void longestNameOneLength() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.addAnimal(new Animal("White dog", DOG, F, 12, 120, 60, true));
        zoo.addAnimal(new Animal("Black dog", DOG, M, 10, 130, 65, true));
        //act
        Animal.Sex sex = zoo.whatSexAmountMore();
        //assert
        assertThat(sex).isEqualTo(F);
    }
}
