package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;

public class ServiceWithLock implements PersonDatabase {
    final List<Person> people = new ArrayList<>();
    ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        people.add(person);
        lock.writeLock().unlock();
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        people.removeIf(person -> person.id() == id);
        lock.writeLock().unlock();
    }

    @Override
    public @Nullable Person findByName(String name) {
        lock.readLock().lock();
        Person result = people.stream().filter(person -> person.name().equals(name)).findFirst().orElse(null);
        lock.readLock().unlock();
        return result;
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        lock.readLock().lock();
        Person result = people.stream().filter(person -> person.address().equals(address)).findFirst().orElse(null);
        lock.readLock().unlock();
        return result;
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        lock.readLock().lock();
        Person result = people.stream().filter(person -> person.phoneNumber().equals(phone)).findFirst().orElse(null);
        lock.readLock().unlock();
        return result;
    }
}
