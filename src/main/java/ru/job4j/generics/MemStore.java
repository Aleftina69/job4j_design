package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        if (model == null) {
            throw new IllegalArgumentException();
        }
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (id == null || model == null) {
            throw new IllegalArgumentException();
        }
        return storage.replace(id, model) != null;
    }

    @Override
    public void delete(String id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        storage.remove(id);
    }

    @Override
    public T findById(String id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        return storage.get(id);
    }
}