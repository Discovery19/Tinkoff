package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {
    @Test
    @DisplayName("Стандартная сотрировка от меньшего к большему по высоте")
    void sortAnimalsByHeight(){
        //arrange
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Animal("White dog", DOG, F, 12 , 120, 60, true));
        zoo.setAnimal(new Animal("Black dog", DOG, M, 10 , 130, 65, true));
        zoo.setAnimal(new Animal("Black cat", CAT, M, 8 , 80, 30, true));
        //act
        List<Animal> zooAfterSortByHeight = zoo.sortAnimalsByHeight();
        //assert
        assertThat(zooAfterSortByHeight).isEqualTo(List.of(
             new Animal("Black cat", CAT, M, 8, 80, 30, true)
            ,new Animal("White dog", DOG, F, 12 , 120, 60, true)
            ,new Animal("Black dog", DOG, M, 10, 130, 65, true)));
    }
    @Test
    @DisplayName("Пустой зоопарк")
    void sortAnimalsByHeightNull(){
        //arrange
        Zoo zoo = new Zoo();
        //act
        List<Animal> zooAfterSortByHeight = zoo.sortAnimalsByHeight();
        //assert
        assertThat(zooAfterSortByHeight).isEqualTo(List.of());
    }
}
