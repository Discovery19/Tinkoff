package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {
    @Test
    @DisplayName("Стандартная сотрировка от большего к меньшему по весу взять первые 2 животных")
    void sortAnimalsByWeightGetFirstK() {
        //arrange
        Zoo zoo = new Zoo();
        zoo.addAnimal(new Animal("White dog", DOG, F, 12, 120, 60, true));
        zoo.addAnimal(new Animal("Black dog", DOG, M, 10, 130, 65, true));
        zoo.addAnimal(new Animal("Black cat", CAT, M, 8, 80, 30, true));
        //act
        List<Animal> zooAfterSortByWeightFirstK = zoo.sortAnimalsByWeightGetKFirst(2);

        //assert
        assertThat(zooAfterSortByWeightFirstK).isEqualTo(List.of(
            new Animal("Black dog", DOG, M, 10, 130, 65, true)
            , new Animal("White dog", DOG, F, 12, 120, 60, true)));
    }

    @Test
    @DisplayName("Пустой зоопарк взять первые k зверей")
    void sortAnimalsByHeightNull() {
        //arrange
        Zoo zoo = new Zoo();
        //act
        List<Animal> zooAfterSortByWeightFirstK = zoo.sortAnimalsByWeightGetKFirst(2);
        //assert
        assertThat(zooAfterSortByWeightFirstK).isEqualTo(List.of());
    }
}