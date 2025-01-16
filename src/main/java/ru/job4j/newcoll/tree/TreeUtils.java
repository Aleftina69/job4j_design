package ru.job4j.newcoll.tree;

import ru.job4j.collection.SimpleQueue;
import ru.job4j.collection.SimpleStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TreeUtils<T> {
    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        int result = 0;
        while (!queue.isEmpty()) {
            var curretNode = queue.poll();
            curretNode.getChildren().forEach(queue::push);
            result++;
        }
        return result;
    }

    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }

        List<T> result = new ArrayList<>();
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            var currentNode = queue.poll();
            result.add(currentNode.getValue());
            currentNode.getChildren().forEach(queue::push);
        }
        return result;
    }
    
    public boolean add(Node<T> root, T parent, T child) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        if (findByKey(root, child).isPresent()) {
            return false;
        }
        Optional<Node<T>> parentNode = findByKey(root, parent);
        if (parentNode.isEmpty()) {
            return false;
        }
        parentNode.get().setValue(child);
        return true;
    }

    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            var currentNode = stack.pop();
            if (currentNode.getValue().equals(key)) {
                return Optional.of(currentNode);
            }
            currentNode.getChildren().forEach(stack::push);
        }
            return Optional.empty();
    }

    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        Optional<Node<T>> parentNode = findByKey(root, key);
        if (parentNode.isPresent()) {

        }
        return null;
    }
}