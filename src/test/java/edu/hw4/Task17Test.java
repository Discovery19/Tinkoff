package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.SPIDER;
import static org.assertj.core.api.Assertions.assertThat;

class Task17Test {
    @Test
    @DisplayName("Собаки кусаются чаще")
    void spidersLessStandard() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("Dog", DOG, F, 12, 120, 60, true));
        zoo.setAnimal(new Animal("Black dog", DOG, M, 10, 130, 65, true));
        zoo.setAnimal(new Animal("Just cat", CAT, M, 8, 80, 30, true));
        zoo.setAnimal(new Animal("Just spider", SPIDER, M, 8, 80, 30, false));
        //act
        boolean res = zoo.spidersBitesMore();
        //assert
        assertThat(res).isFalse();
    }

    @Test
    @DisplayName("Пауки кусаются чаще")
    void spidersMoreStandard() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("Spider", SPIDER, F, 12, 120, 60, true));
        zoo.setAnimal(new Animal("Black dog", DOG, M, 10, 130, 65, false));
        zoo.setAnimal(new Animal("Just cat", CAT, M, 8, 80, 30, true));
        zoo.setAnimal(new Animal("Just spider", SPIDER, M, 8, 80, 30, true));
        //act
        boolean res = zoo.spidersBitesMore();
        //assert
        assertThat(res).isTrue();
    }

    @Test
    @DisplayName("Нет животных")
    void spidersNoAnimals() {
        //arrange
        Zoo zoo = new Zoo();
        //act
        boolean res = zoo.spidersBitesMore();
        //assert
        assertThat(res).isFalse();
    }
}
