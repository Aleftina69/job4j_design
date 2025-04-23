package ru.job4j.collection;

public interface Queue<T> {
     T pool();
     T remove();
     void push(T t);
     boolean isEmpty();

}
