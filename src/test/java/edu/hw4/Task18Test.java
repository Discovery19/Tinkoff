package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.FISH;
import static edu.hw4.Animal.Type.SPIDER;
import static org.assertj.core.api.Assertions.assertThat;

class Task18Test {
    @Test
    @DisplayName("Самая тяжелая рыба")
    void fishStandard() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("Fish", FISH, F, 12, 120, 60, true));
        zoo.setAnimal(new Animal("Just spider", SPIDER, M, 8, 80, 30, false));
        Zoo zoo2 = new Zoo();
        zoo2.setAnimal(new Animal("Fish", FISH, F, 12, 120, 50, true));
        Zoo zoo3 = new Zoo();
        zoo3.setAnimal(new Animal("Fish", FISH, F, 12, 120, 40, true));
        //act
        Animal res = zoo.heaviestFish(List.of(zoo.getAnimals(), zoo2.getAnimals(), zoo3.getAnimals()));
        //assert
        assertThat(res).isEqualTo(new Animal("Fish", FISH, F, 12, 120, 60, true));
    }

    @Test
    @DisplayName("Нет животных")
    void fishNoAnimals() {
        //arrange
        Zoo zoo = new Zoo();
        //act
        Animal res = zoo.heaviestFish(List.of());
        //assert
        assertThat(res).isNull();
    }
}
