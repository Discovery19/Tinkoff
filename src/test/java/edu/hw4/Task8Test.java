package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static org.assertj.core.api.Assertions.assertThat;

class Task8Test {
    @Test
    @DisplayName("Самое тяжелое животное")
    void heaviestAnimalStandard() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.addAnimal(new Animal("Dog", DOG, F, 12, 120, 60, true));
        zoo.addAnimal(new Animal("Black dog", DOG, M, 10, 130, 65, true));
        zoo.addAnimal(new Animal("Just cat", CAT, M, 8, 80, 30, true));
        //act
        Optional<Animal> animal = zoo.theHeaviestAnimalBelowKHeight(125);
        //assert
        assertThat(animal).contains(new Animal("Dog", DOG, F, 12, 120, 60, true));
    }

    @Test
    @DisplayName("Нет животных")
    void heaviestAnimalNoAnimals() {
        //arrange
        Zoo zoo = new Zoo();
        //act
        Optional<Animal> animal = zoo.theHeaviestAnimalBelowKHeight(135);
        //assert
        assertThat(animal).isEmpty();
    }
}
