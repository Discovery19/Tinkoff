package edu.hw3;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Task8 {
    private Task8() {
    }

    static class BackIterator<T> implements Iterator<T> {
        private ListIterator<T> iterator;

        BackIterator(List<T> list) {
            iterator = list.listIterator(list.size());
        }

        @Override
        public boolean hasNext() {
            return iterator.hasPrevious();
        }

        @Override
        public T next() {
            return iterator.previous();
        }

    }
}


