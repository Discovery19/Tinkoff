package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static org.assertj.core.api.Assertions.assertThat;

class Task7Test {
    @Test
    @DisplayName("Самое старое животное")
    void oldestAnimalStandard() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("Dog", DOG, F, 12, 120, 60, true));
        zoo.setAnimal(new Animal("Black dog", DOG, M, 10, 130, 65, true));
        zoo.setAnimal(new Animal("Just cat", CAT, M, 8, 80, 30, true));
        //act
        Animal animal = zoo.theOldestAnimal();
        //assert
        assertThat(animal)
            .isEqualTo(new Animal("Dog", DOG, F, 12, 120, 60, true));
    }

    @Test
    @DisplayName("Нет животных")
    void oldestAnimalNoAnimals() {
        //arrange
        Zoo zoo = new Zoo();
        //act
        Animal animal = zoo.theOldestAnimal();
        //assert
        assertThat(animal).isNull();
    }
}
