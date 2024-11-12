package ru.job4j.serialization.json;

import java.util.Arrays;

public class User {
    private final String name;
    private final int age;
    private final boolean children;
    private final Contact contact;
    private final String[] family;

    public User(String name, int age, boolean children, Contact contact, String[] family) {
        this.name = name;
        this.age = age;
        this.children = children;
        this.contact = contact;
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name=" + name
                + ", age=" + age
                + ", children=" + children
                + ", contact=" + contact
                + ", family=" + Arrays.toString(family)
                + '}';
    }
}
