package edu.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Zoo {
    private final static int ONE_HUNDRED = 100;
    private final List<Animal> animals = new ArrayList<>();

    public void setAnimal(Animal animal) {
        animals.add(animal);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Animal> sortAnimalsByHeight() {
        return animals.stream().sorted((o1, o2) -> Integer.compare(o1.height(), o2.height()))
            .collect(Collectors.toList());
    }

    public List<Animal> sortAnimalsByWeight() {
        return animals.stream().sorted((o1, o2) -> (-1) * Integer.compare(o1.weight(), o2.weight()))
            .collect(Collectors.toList());
    }

    private boolean predicate() {
        return true;
    }

    public List<Animal> sortAnimalsByWeightGetKFirst(int k) {
        return animals.stream().sorted((o1, o2) -> (-1) * Integer.compare(o1.weight(), o2.weight()))
            .filter(x -> predicate())
            .limit(k)
            .collect(Collectors.toList());
    }

    public Map<Animal.Type, Integer> getAnimalTypes() {
        return animals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(e -> 1)));
    }

    public Animal longestAnimalName() {
        return animals.stream().max(Comparator.comparingInt(e -> e.name().length())).orElse(null);
    }

    public Animal.Sex whatSexAmountMore() {
        return animals.stream().filter(a -> a.sex() == Animal.Sex.M).count()
            > animals.stream().filter(a -> a.sex() == Animal.Sex.F).count()
            ? Animal.Sex.M : Animal.Sex.F;
    }

    public Animal theHeaviestAnimal() {
        return animals.stream().max(Comparator.comparingInt(Animal::weight)).orElse(null);
    }

    public Animal theOldestAnimal() {
        return animals.stream().max(Comparator.comparingInt(Animal::age)).orElse(null);
    }

    public Optional<Animal> theHeaviestAnimalBelowKHeight(int k) {
        return animals.stream().filter(e -> e.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public int countPaws() {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public List<Animal> ageIsNotEqualToTheNumberOfPaws() {
        return animals.stream().filter(e -> e.paws() != e.age()).toList();
    }

    public List<Animal> animalsThatCanBite() {
        return animals.stream().filter(e -> e.bites() && e.height() > ONE_HUNDRED).toList();
    }

    public List<Animal> weightMoreThanHeight() {
        return animals.stream().filter(e -> e.weight() > e.height()).toList();
    }

    public List<Animal> animalsWithDoubleNames() {
        return animals.stream().filter(e -> e.name().split(" ").length > 1).toList();
    }

    public boolean dogHeightMoreThanK(int k) {
        return animals.stream().anyMatch(e -> e.height() > k);
    }

    public int summarizeAnimalWeight(int lowestAge, int highestAge) {
        return animals.stream().filter(e -> e.age() >= lowestAge && e.age() <= highestAge).mapToInt(Animal::weight)
            .sum();
    }

    public List<Animal> sortTypeSexName() {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name)).toList();
    }

    public boolean spidersBitesMore() {
        float spidersBites = animals.stream().filter(e -> e.type().equals(Animal.Type.SPIDER) && e.bites()).count();
        float allSpiders = animals.stream().filter(e -> e.type().equals(Animal.Type.SPIDER)).count();
        float dogsBites = animals.stream().filter(e -> e.type().equals(Animal.Type.DOG) && e.bites()).count();
        float allDogs = animals.stream().filter(e -> e.type().equals(Animal.Type.DOG)).count();
        return spidersBites / allSpiders > dogsBites / allDogs;
    }

    public Animal heaviestFish(List<List<Animal>> aquariums) {
        return aquariums.stream().flatMap(List::stream).filter(e -> e.type().equals(Animal.Type.FISH))
            .max(Comparator.comparingInt(Animal::weight)).orElse(null);
    }

    public void checkValidation() throws ValidationError {
        Optional<Animal> invalidAnimal = animals.stream()
            .filter(animal -> animal.age() > ONE_HUNDRED || animal.age() < 0)
            .findFirst();
        if (invalidAnimal.isPresent()) {
            throw new ValidationError("Invalid age: " + invalidAnimal);
        }

        invalidAnimal = animals.stream()
            .filter(animal -> animal.name().isEmpty())
            .findFirst();
        if (invalidAnimal.isPresent()) {
            throw new ValidationError("Invalid name: " + invalidAnimal);
        }

        invalidAnimal = animals.stream()
            .filter(animal -> animal.weight() < 0)
            .findFirst();
        if (invalidAnimal.isPresent()) {
            throw new ValidationError("Invalid weight: " + invalidAnimal);
        }

        invalidAnimal = animals.stream()
            .filter(animal -> animal.height() < 0)
            .findFirst();
        if (invalidAnimal.isPresent()) {
            throw new ValidationError("Invalid height: " + invalidAnimal);
        }
    }

    public Map<String, String> animalsValidationNoThrowError() {
        Map<String, String> errors = new HashMap<>();
        animals.stream()
            .filter(animal -> animal.age() > ONE_HUNDRED || animal.age() < 0)
            .findFirst()
            .ifPresent(animal -> errors.put(animal.name(), String.valueOf(animal.age())));

        animals.stream()
            .filter(animal -> animal.name().isEmpty())
            .findFirst()
            .ifPresent(animal -> errors.put(String.valueOf(animal.type()), animal.name()));

        animals.stream()
            .filter(animal -> animal.weight() < 0)
            .findFirst()
            .ifPresent(animal -> errors.put(animal.name(), String.valueOf(animal.weight())));

        animals.stream()
            .filter(animal -> animal.height() < 0)
            .findFirst()
            .ifPresent(animal -> errors.put(animal.name(), String.valueOf(animal.height())));

        return errors;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Animal a : animals) {
            sb.append(a.toString()).append("\n");
        }
        return sb.toString();
    }
}
