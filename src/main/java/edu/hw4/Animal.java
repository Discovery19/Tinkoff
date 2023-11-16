package edu.hw4;

import org.jetbrains.annotations.NotNull;

public record Animal(
    @NotNull
    String name,
    @NotNull
    Type type,
    @NotNull
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }
    //CHECKSTYLE:OFF: checkstyle:magicnumber
    public int paws() {
        return switch (type) {
            case CAT, DOG -> 4;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> 8;
        };
    }

    @Override public String toString() {
        return "Animal{"
            + "name='" + name
            + ", type=" + type
            + ", sex=" + sex
            + ", age=" + age
            + ", height=" + height
            + ", weight=" + weight
            + ", bites=" + bites
            + '}';
    }
}
