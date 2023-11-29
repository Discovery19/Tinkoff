package edu.hw7.Task3;

import java.util.HashMap;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@Getter public class SynchronizedPersonDatabase implements PersonDatabase {
    private final HashMap<Integer, Person> people = new HashMap<>();
    private final HashMap<String, Person> nameMap = new HashMap<>();
    private final HashMap<String, Person> addressMap = new HashMap<>();
    private final HashMap<String, Person> phoneMap = new HashMap<>();

    @Override
    public void add(Person person) {
        synchronized (people) {
            people.put(person.id(), person);
        }
        synchronized (nameMap) {
            nameMap.put(person.name(), person);
        }
        synchronized (addressMap) {
            addressMap.put(person.address(), person);
        }
        synchronized (phoneMap) {
            phoneMap.put(person.phoneNumber(), person);
        }
    }

    @Override
    public void delete(int id) {
        String name = people.get(id).name();
        String address = people.get(id).address();
        String phone = people.get(id).phoneNumber();
        synchronized (people) {
            people.remove(id);
        }
        synchronized (nameMap) {
            nameMap.remove(name);
        }
        synchronized (addressMap) {
            addressMap.remove(address);
        }
        synchronized (phoneMap) {
            phoneMap.remove(phone);
        }
    }

    @Override
    public @Nullable Person findByName(String name) {
        synchronized (nameMap) {
            return nameMap.get(name);
        }
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        synchronized (addressMap) {
            return addressMap.get(address);
        }
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        synchronized (phoneMap) {
            return phoneMap.get(phone);
        }
    }
}
