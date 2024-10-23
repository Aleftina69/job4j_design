package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if ((count + 1) > (capacity * LOAD_FACTOR)) {
            expand();
        }
        int index = findIndex(key);
        if (index == -1 || table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = findIndex(key);
        if (table[index] != null && keyComparison(table[index].key, key)) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = findIndex(key);
        if (table[index] != null && keyComparison(table[index].key, key)) {
            table[index] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int index = 0;
            private final int expectedModCount = modCount;
            private boolean result = false;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                result = false;
                while (index < table.length && !result) {
                    if (table[index] != null) {
                        result = true;
                    } else {
                        index++;
                    }
                }
                return result;
            }

            @Override
            public K next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                K key = table[index].key;
                index++;
                return key;
            }
        };
    }

    private int findIndex(K key) {
        int hashCode = Objects.hashCode(key);
        int hash = hash(hashCode);
        return indexFor(hash);
    }

    private boolean keyComparison(K key1, K key2) {
           return Objects.hashCode(key1) == Objects.hashCode(key2)
                   && Objects.equals(key1, key2);
    }

    private int hash(int hashCode) {
        return (hashCode >>> 16) ^ hashCode;
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        capacity *= 2;
        table = new MapEntry[capacity];
        count = 0;
        for (MapEntry<K, V> entry : oldTable) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
