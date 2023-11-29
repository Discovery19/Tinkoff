package edu.hw7.Task3;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@Getter public class UsingLockPersonDatabase implements PersonDatabase {
    ReadWriteLock lock = new ReentrantReadWriteLock();
    private final HashMap<Integer, Person> people = new HashMap<>();
    private final HashMap<String, Person> nameMap = new HashMap<>();
    private final HashMap<String, Person> addressMap = new HashMap<>();
    private final HashMap<String, Person> phoneMap = new HashMap<>();

    @Override public void add(Person person) {
        try {
            lock.writeLock().lock();
            people.put(person.id(), person);
            nameMap.put(person.name(), person);
            addressMap.put(person.address(), person);
            phoneMap.put(person.phoneNumber(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override public void delete(int id) {

        String name = people.get(id).name();
        String address = people.get(id).address();
        String phone = people.get(id).phoneNumber();
        try {
            lock.writeLock().lock();
            people.remove(id);
            nameMap.remove(name);
            addressMap.remove(address);
            phoneMap.remove(phone);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByName(String name) {
        try {
            lock.readLock().lock();
            return nameMap.get(name);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        try {
            lock.readLock().lock();
            return addressMap.get(address);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        try {
            lock.readLock().lock();
            return phoneMap.get(phone);
        } finally {
            lock.readLock().unlock();
        }
    }
}
