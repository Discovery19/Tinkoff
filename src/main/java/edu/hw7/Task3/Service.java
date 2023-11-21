package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@Getter public class Service implements PersonDatabase {
    private final List<Person> people = new ArrayList<>();

    @Override
    public void add(Person person) {
        synchronized (people) {
            people.add(person);
        }
    }

    @Override
    public void delete(int id) {
        synchronized (people) {
            people.removeIf(person -> person.id() == id);
        }
    }

    @Override
    public @Nullable Person findByName(String name) {
        synchronized (people) {
            return people.stream().filter(person -> person.name().equals(name)).findFirst().orElse(null);
        }
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        synchronized (people) {
            return people.stream().filter(person -> person.address().equals(address)).findFirst().orElse(null);
        }
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        synchronized (people) {
            return people.stream().filter(person -> person.phoneNumber().equals(phone)).findFirst().orElse(null);
        }
    }
}
