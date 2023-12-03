package edu.hw7.Task3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@Getter public class UsingLockPersonDatabase implements PersonDatabase {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<Integer, Person> people = new HashMap<>();
    private final Map<String, Person> nameMap = new HashMap<>();
    private final Map<String, Person> addressMap = new HashMap<>();
    private final Map<String, Person> phoneMap = new HashMap<>();

    @Override public void add(Person person) {
        lock.writeLock().lock();
        try {
            people.put(person.id(), person);
            nameMap.put(person.name(), person);
            addressMap.put(person.address(), person);
            phoneMap.put(person.phoneNumber(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override public void delete(int id) {
        lock.writeLock().lock();
        try {
            lock.readLock().lock();
            try {
                if (people.get(id) != null) {
                    String name = people.get(id).name();
                    String address = people.get(id).address();
                    String phone = people.get(id).phoneNumber();
                    people.remove(id);
                    nameMap.remove(name);
                    addressMap.remove(address);
                    phoneMap.remove(phone);
                }
            } finally {
                lock.readLock().unlock();
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByName(String name) {
        lock.readLock().lock();
        try {
            return nameMap.get(name);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        lock.readLock().lock();
        try {
            return addressMap.get(address);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return phoneMap.get(phone);
        } finally {
            lock.readLock().unlock();
        }
    }
}
