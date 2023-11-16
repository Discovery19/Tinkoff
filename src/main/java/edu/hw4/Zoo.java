package edu.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Zoo {
    private static final int ONE_HUNDRED = 100;
    private final List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    //task1
    public List<Animal> sortAnimalsByHeight() {
        return animals.stream().sorted(Comparator.comparing(Animal::height)).toList();
    }

    //task2
    public List<Animal> sortAnimalsByWeightGetKFirst(int k) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .limit(k).toList();
    }

    //task3
    public Map<Animal.Type, Integer> getAnimalTypes() {
        return animals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(e -> 1)));
    }

    //task4
    public Animal longestAnimalName() {
        return animals.stream().max(Comparator.comparingInt(e -> e.name().length())).orElse(null);
    }

    //task5
    public Animal.Sex whatSexAmountMore() {
        return animals.stream().filter(a -> a.sex() == Animal.Sex.M).count()
            > animals.stream().filter(a -> a.sex() == Animal.Sex.F).count()
            ? Animal.Sex.M : Animal.Sex.F;
    }

    //task6
    public Map<Animal.Type, Animal> theHeaviestAnimal() {
        return animals.stream()
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.collectingAndThen(
                    Collectors.maxBy(Comparator.comparingInt(Animal::weight)),
                    optionalAnimal -> optionalAnimal.orElse(null)
                )
            ));
    }

    //task7
    public Animal theOldestAnimal(int k) {
        return animals.size() >= k ?
            animals.stream().sorted(Comparator.comparing(Animal::age)).limit(k).toList().getLast() : null;
    }

    //task8
    public Optional<Animal> theHeaviestAnimalBelowKHeight(int k) {
        return animals.stream().filter(e -> e.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    //task9
    public Integer countPaws() {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    //task10
    public List<Animal> ageIsNotEqualToTheNumberOfPaws() {
        return animals.stream().filter(e -> e.paws() != e.age()).toList();
    }

    //task11
    public List<Animal> animalsThatCanBite() {
        return animals.stream().filter(e -> e.bites() && e.height() > ONE_HUNDRED).toList();
    }

    //task12-
    public Integer weightMoreThanHeight() {
        return (int) animals.stream().filter(e -> e.weight() > e.height()).count();
    }

    //task13
    public List<Animal> animalsWithDoubleNames() {
        return animals.stream().filter(e -> e.name().contains(" ")).toList();
    }

    //task14
    public boolean dogHeightMoreThanK(int k) {
        return animals.stream().anyMatch(e -> e.type() == Animal.Type.DOG && e.height() > k);
    }

    //task15
    public Map<Animal.Type, Integer> summarizeAnimalWeight(int lowestAge, int highestAge) {
        return animals.stream()
            .filter(animal -> animal.age() >= lowestAge && animal.age() <= highestAge)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    //task16
    public List<Animal> sortTypeSexName() {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name)).toList();
    }

    //task17
    public boolean spidersBitesMore() {
        float spidersBites = animals.stream().filter(e -> e.type().equals(Animal.Type.SPIDER) && e.bites()).count();
        float allSpiders = animals.stream().filter(e -> e.type().equals(Animal.Type.SPIDER)).count();
        float dogsBites = animals.stream().filter(e -> e.type().equals(Animal.Type.DOG) && e.bites()).count();
        float allDogs = animals.stream().filter(e -> e.type().equals(Animal.Type.DOG)).count();
        return !(allDogs == 0 || allSpiders == 0) && spidersBites / allSpiders > dogsBites / allDogs;
    }

    //task18
    public Animal heaviestFish(List<List<Animal>> aquariums) {
        return aquariums.stream().flatMap(List::stream).filter(e -> e.type().equals(Animal.Type.FISH))
            .max(Comparator.comparingInt(Animal::weight)).orElse(null);
    }

    //task19/20
    public Map<String, String> animalValidation() {
        Map<String, Set<ValidationError>> errors = new HashMap<>();
        for (Animal animal : animals) {
            Set<ValidationError> set = checkAnimalValidation(animal);
            if (!set.isEmpty()) {
                String name = !animal.name().isEmpty() ? animal.name() : animal.type().toString();
                errors.put(name, set);
            }
        }
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, Set<ValidationError>> entry : errors.entrySet()) {
            result.put(entry.getKey(), entry.getValue().stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString());
        }
        return result;
    }

    public Set<ValidationError> checkAnimalValidation(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();
        if (animal.age() > ONE_HUNDRED || animal.age() < 0) {
            errors.add(new ValidationError("Invalid age"));
        }
        if (animal.name().isEmpty()) {
            errors.add(new ValidationError("Invalid name"));
        }
        if (animal.weight() <= 0) {
            errors.add(new ValidationError("Invalid weight"));
        }
        if (animal.height() <= 0) {
            errors.add(new ValidationError("Invalid height"));
        }
        return errors;
    }

//    public Map<String, Set<ValidationError>> checkValidation() {
//        Map<String, Set<ValidationError>> result = new HashMap<>();
//
//        Optional<Animal> invalidAnimal = animals.stream()
//            .filter(animal -> animal.age() > ONE_HUNDRED || animal.age() < 0)
//            .findFirst();
//        if (invalidAnimal.isPresent()) {
//            result.put(
//                invalidAnimal.get().name(),
//                //CHECKSTYLE:OFF: checkstyle:MultipleStringLiterals
//                Set.of(new ValidationError("Invalid age"))
//            );
//        }
//
//        invalidAnimal = animals.stream()
//            .filter(animal -> animal.name().isEmpty())
//            .findFirst();
//        if (invalidAnimal.isPresent()) {
//            if (!result.containsKey(invalidAnimal.get().name())) {
//                result.put(
//                    invalidAnimal.get().name(),
//                    Set.of(new ValidationError("Invalid name"))
//                );
//            } else {
//                result.get(invalidAnimal.get().name()).add(new ValidationError("Invalid name"));
//            }
//        }
//
//        invalidAnimal = animals.stream()
//            .filter(animal -> animal.weight() <= 0)
//            .findFirst();
//        if (invalidAnimal.isPresent()) {
//            if (!result.containsKey(invalidAnimal.get().name())) {
//                result.put(
//                    invalidAnimal.get().name(),
//                    Set.of(new ValidationError("Invalid weight"))
//                );
//            } else {
//                result.get(invalidAnimal.get().name()).add(new ValidationError("Invalid weight"));
//            }
//        }
//
//        invalidAnimal = animals.stream()
//            .filter(animal -> animal.height() < 0)
//            .findFirst();
//        if (invalidAnimal.isPresent()) {
//            if (!result.containsKey(invalidAnimal.get().name())) {
//                result.put(
//                    invalidAnimal.get().name(),
//                    Set.of(new ValidationError("Invalid height"))
//                );
//            } else {
//                result.get(invalidAnimal.get().name()).add(new ValidationError("Invalid height"));
//            }
//        }
//        return result;
//    }

//    public Map<String, String> animalsValidationNoThrowError() {
//        Map<String, String> errors = new HashMap<>();
//        animals.stream()
//            .filter(animal -> animal.age() > ONE_HUNDRED || animal.age() < 0)
//            .findFirst()
//            .ifPresent(animal -> errors.put(animal.name(), String.valueOf(animal.age())));
//
//        animals.stream()
//            .filter(animal -> animal.name().isEmpty())
//            .findFirst()
//            .ifPresent(animal -> errors.put(String.valueOf(animal.type()), animal.name()));
//
//        animals.stream()
//            .filter(animal -> animal.weight() < 0)
//            .findFirst()
//            .ifPresent(animal -> errors.put(animal.name(), String.valueOf(animal.weight())));
//
//        animals.stream()
//            .filter(animal -> animal.height() < 0)
//            .findFirst()
//            .ifPresent(animal -> errors.put(animal.name(), String.valueOf(animal.height())));
//
//        return errors;
//    }
}
