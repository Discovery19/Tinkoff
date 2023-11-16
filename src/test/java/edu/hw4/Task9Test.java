package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.FISH;
import static org.assertj.core.api.Assertions.assertThat;

class Task9Test {
    @Test
    @DisplayName("Подсчет лап животных")
    void pawsStandard() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.addAnimal(new Animal("Dog", DOG, F, 12, 120, 60, true));
        zoo.addAnimal(new Animal("Black dog", DOG, M, 10, 130, 65, true));
        zoo.addAnimal(new Animal("Just cat", CAT, M, 8, 80, 30, true));
        zoo.addAnimal(new Animal("Just fish", FISH, M, 8, 80, 30, true));
        //act
        int paws = zoo.countPaws();
        //assert
        assertThat(paws).isEqualTo(12);
    }

    @Test
    @DisplayName("Нет животных")
    void pawsNoAnimals() {
        //arrange
        Zoo zoo = new Zoo();
        //act
        int paws = zoo.countPaws();
        //assert
        assertThat(paws).isZero();
    }
}
