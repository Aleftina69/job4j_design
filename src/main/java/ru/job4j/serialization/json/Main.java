package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final User user = new User("Ivan", 35, true, new Contact("+79371345678"),
                new String[] {"unknown", "unknown"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(user));
        final String userJson =
                "{"
                        + "\"name\":Ivan,"
                        + "\"age\":35,"
                        + "\"children\":true,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+79371345678\""
                        + "},"
                        + "\"family\":"
                        + "[\"wife\",\"son\", \"daughter\"]"
                        + "}";
        final User personMod = gson.fromJson(userJson, User.class);
        System.out.println(personMod);
    }
}
