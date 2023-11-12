package edu.hw3;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Task8 {
    private Task8() {
    }

    static class BackIterator<T> implements Iterator<T> {
        private List<T> elements;
        private int index;

        BackIterator(Collection<T> collection) {
            this.elements = List.copyOf(collection);
            index = elements.size() - 1;
        }

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public T next() {
            return elements.get(index--);
        }
    }
}


