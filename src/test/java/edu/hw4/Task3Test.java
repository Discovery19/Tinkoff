package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {
    @Test
    @DisplayName("Обычное разделение по типам")
    void typesOfAnimalsStandard() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("White dog", DOG, F, 12, 120, 60, true));
        zoo.setAnimal(new Animal("Black dog", DOG, M, 10, 130, 65, true));
        zoo.setAnimal(new Animal("Black cat", CAT, M, 8, 80, 30, true));
        //act
        Map<Animal.Type, Integer> result = zoo.getAnimalTypes();
        //assert
        assertThat(result).containsEntry(DOG, 2).containsEntry(CAT, 1);
    }

    @Test
    @DisplayName("Разделение по типам, один тип")
    void typesOfAnimalsOneType() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("White dog", DOG, F, 12, 120, 60, true));
        zoo.setAnimal(new Animal("Black dog", DOG, M, 10, 130, 65, true));
        //act
        Map<Animal.Type, Integer> result = zoo.getAnimalTypes();
        //assert
        assertThat(result).containsEntry(DOG, 2);
    }

    @Test
    @DisplayName("Разделение по типам, нет животных")
    void typesOfAnimalsNoAnimals() {
        //arrange
        Zoo zoo = new Zoo();
        //act
        Map<Animal.Type, Integer> result = zoo.getAnimalTypes();
        //assert
        assertThat(result).isEmpty();
    }
}
