package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Type.BIRD;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task19Test {
    @Test
    @DisplayName("Выбрасывание ошибки рост")
    void throwValidationHeight() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("Bird", BIRD, F, 12, -120, 160, false));
        //act/assert
        assertThrows(ValidationError.class, zoo::checkValidation);
    }
    @Test
    @DisplayName("Выбрасывание ошибки возраст")
    void throwValidationAge() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("Bird", BIRD, F, -12, 120, 160, false));
        //act/assert
        assertThrows(ValidationError.class, zoo::checkValidation);
    }
    @Test
    @DisplayName("Выбрасывание ошибки вес")
    void throwValidationWeight() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("Bird", BIRD, F, 12, 120, 1-60, false));
        //act/assert
        assertThrows(ValidationError.class, zoo::checkValidation);
    }
    @Test
    @DisplayName("Выбрасывание ошибки имя")
    void throwValidationName() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("", BIRD, F, 12, 120, 160, false));
        //act/assert
        assertThrows(ValidationError.class, zoo::checkValidation);
    }
}
