package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean result = false;
        if (!contains(value)) {
            set.add(value);
            result = true;
        }
        return result;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;
        for (int i = 0; i < set.size(); i++) {
            T element = set.get(i);
            if ((element == null && value == null) || (element != null && element.equals(value))) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}