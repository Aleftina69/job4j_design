package ru.job4j.newcoll.tree;

import ru.job4j.collection.SimpleQueue;

import java.util.ArrayList;
import java.util.List;

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
}