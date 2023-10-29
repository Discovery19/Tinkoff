package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Type.BIRD;
import static org.assertj.core.api.Assertions.assertThat;

class Task20Test {
    @Test
    @DisplayName("Ошибка рост")
    void throwValidationHeight() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("Bird", BIRD, F, 12, -120, 160, false));
        //act
        var result = zoo.animalsValidationNoThrowError();
        //assert
        assertThat(result).containsEntry("Bird", String.valueOf(-120));
    }
    @Test
    @DisplayName("Ошибка возраст")
    void throwValidationAge() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("Bird", BIRD, F, -12, 120, 160, false));
        zoo.setAnimal(new Animal("Bird right", BIRD, F, 12, 120, 160, false));
        //act
        var result = zoo.animalsValidationNoThrowError();
        //assert
        assertThat(result).containsEntry("Bird", String.valueOf(-12));
    }
    @Test
    @DisplayName("Ошибка вес и имя")
    void throwValidationWeight() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("Bird", BIRD, F, 12, -120, 1-60, false));
        zoo.setAnimal(new Animal("", BIRD, F, 10, 120, 160, false));
        zoo.setAnimal(new Animal("Bird right", BIRD, F, 12, 120, 160, false));
        //act
        var result = zoo.animalsValidationNoThrowError();
        //assert
        assertThat(result).containsAllEntriesOf(Map.of("Bird", String.valueOf(-120),"BIRD", ""));
    }
    @Test
    @DisplayName("Ошибка вес и рост в одном, вывод роста")
    void throwValidation() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("Bird", BIRD, F, 12, -120, 1-60, false));
        zoo.setAnimal(new Animal("Bird right", BIRD, F, 12, 120, 160, false));
        //act
        var result = zoo.animalsValidationNoThrowError();
        //assert
        assertThat(result).containsAllEntriesOf(Map.of("Bird", String.valueOf(-120)));
    }
    @Test
    @DisplayName("Ошибка имя")
    void throwValidationName() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("", BIRD, F, 12, 120, 160, false));
        zoo.setAnimal(new Animal("Bird right", BIRD, F, 12, 120, 160, false));
        //act
        var result = zoo.animalsValidationNoThrowError();
        //assert
        assertThat(result).containsEntry("BIRD", "");
    }
}
