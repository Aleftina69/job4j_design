package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        birthday.set(1991, Calendar.JULY, 21);
        User userFirst = new User("Иван", 1, birthday);
        int hashCodeFirst = userFirst.hashCode();
        int hashFirst = hashCodeFirst ^ (hashCodeFirst >>> 16);
        int backetFirst = hashFirst & 15;
        User userSecond = new User("Иван", 1, birthday);
        int hashCodeSecond = userSecond.hashCode();
        int hashSecond = hashCodeSecond ^ (hashCodeFirst >>> 16);
        int backetSecond = hashSecond & 15;
        Map<User, Object> map = new HashMap<>();
        map.put(userFirst, new Object());
        map.put(userSecond, new Object());
        System.out.printf("UserFirst - хэш-код : %s, хэш : %s, бакет : %s", hashCodeFirst, hashFirst, backetFirst);
        System.out.println();
        System.out.printf("UserSecond- хэш-код : %s, хэш : %s, бакет : %s", hashCodeSecond, hashSecond, backetSecond);
        System.out.println();
        System.out.println(userFirst.equals(userSecond));

    }
}
