package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {
    @Test
    @DisplayName("Обычное самое длинное имя")
    void longestNameStandard() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("Dog", DOG, F, 12, 120, 60, true));
        zoo.setAnimal(new Animal("Black dog", DOG, M, 10, 130, 65, true));
        zoo.setAnimal(new Animal("Just cat", CAT, M, 8, 80, 30, true));
        //act
        Animal animal = zoo.longestAnimalName();
        //assert
        assertThat(animal)
            .isEqualTo(new Animal("Black dog", DOG, M, 10, 130, 65, true));
    }

    @Test
    @DisplayName("Самое длинное имя, имена одной длинны. Возврат первого имени большей длины")
    void longestNameOneLength() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("White dog", DOG, F, 12, 120, 60, true));
        zoo.setAnimal(new Animal("Black dog", DOG, M, 10, 130, 65, true));
        //act
        Animal animal = zoo.longestAnimalName();
        //assert
        assertThat(animal)
            .isEqualTo(new Animal("White dog", DOG, F, 12, 120, 60, true));
    }

    @Test
    @DisplayName("Нет животных")
    void typesOfAnimalsNoAnimals() {
        //arrange
        Zoo zoo = new Zoo();
        //act
        Animal animal = zoo.longestAnimalName();
        //assert
        assertThat(animal).isEqualTo(null);
    }
}
