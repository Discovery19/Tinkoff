package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@Getter
public class ServiceWithLock implements PersonDatabase {
    final List<Person> people = new ArrayList<>();
    ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            people.add(person);
        } finally {
            lock.writeLock().unlock();
        }

    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            people.removeIf(person -> person.id() == id);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByName(String name) {
        lock.readLock().lock();
        try {
            return people.stream().filter(person -> person.name().equals(name)).findFirst().orElse(null);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        lock.readLock().lock();
        try {
            return people.stream().filter(person -> person.address().equals(address)).findFirst().orElse(null);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return people.stream().filter(person -> person.phoneNumber().equals(phone)).findFirst().orElse(null);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
