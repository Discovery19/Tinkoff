package edu.hw7.Task3;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@Getter public class SynchronizedPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> people = new HashMap<>();
    private final Map<String, Person> nameMap = new HashMap<>();
    private final Map<String, Person> addressMap = new HashMap<>();
    private final Map<String, Person> phoneMap = new HashMap<>();

    @Override
    public void add(Person person) {
        synchronized (this) {
            people.put(person.id(), person);
            nameMap.put(person.name(), person);
            addressMap.put(person.address(), person);
            phoneMap.put(person.phoneNumber(), person);
        }

    }

    @Override
    public void delete(int id) {

        synchronized (this) {
            if (people.get(id) != null) {
                String name = people.get(id).name();
                String address = people.get(id).address();
                String phone = people.get(id).phoneNumber();
                people.remove(id);
                nameMap.remove(name);
                addressMap.remove(address);
                phoneMap.remove(phone);
            }
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
