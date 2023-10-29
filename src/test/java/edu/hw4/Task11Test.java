package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.FISH;
import static org.assertj.core.api.Assertions.assertThat;

class Task11Test {
    @Test
    @DisplayName("Проверка может ли укусить")
    void biteChanceStandard() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("Dog", DOG, F, 12, 120, 60, true));
        zoo.setAnimal(new Animal("Black dog", DOG, M, 10, 130, 65, true));
        zoo.setAnimal(new Animal("Just cat", CAT, M, 4, 80, 30, true));
        zoo.setAnimal(new Animal("Just fish", FISH, M, 8, 80, 30, true));
        //act
        List<Animal> animals = zoo.animalsThatCanBite();
        //assert
        assertThat(animals)
            .isEqualTo(List.of(new Animal("Dog", DOG, F, 12, 120, 60, true)
                , new Animal("Black dog", DOG, M, 10, 130, 65, true)));
    }

    @Test
    @DisplayName("Нет животных")
    void pawsAgeNoAnimals() {
        //arrange
        Zoo zoo = new Zoo();
        //act
        List<Animal> animals = zoo.animalsThatCanBite();
        //assert
        assertThat(animals).isEmpty();
    }
}
