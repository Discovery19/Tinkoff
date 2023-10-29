package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.FISH;
import static org.assertj.core.api.Assertions.assertThat;

class Task15Test {
    @Test
    @DisplayName("Сумма веса в промежутке возраста")
    void sumWeightStandard() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("Dog", DOG, F, 12, 120, 60, true));
        zoo.setAnimal(new Animal("Black dog", DOG, M, 10, 130, 65, true));
        zoo.setAnimal(new Animal("Just cat", CAT, M, 8, 80, 30, true));
        zoo.setAnimal(new Animal("Just fish", FISH, M, 8, 80, 30, true));
        //act
        int res = zoo.summarizeAnimalWeight(8,10);
        //assert
        assertThat(res).isEqualTo(125);
    }
    @Test
    @DisplayName("Нет животных")
    void sumWeightNoAnimals() {
        //arrange
        Zoo zoo = new Zoo();
        //act
        int res = zoo.summarizeAnimalWeight(8,10);
        //assert
        assertThat(res).isZero();
    }
}
