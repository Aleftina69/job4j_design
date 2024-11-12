package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final User user = new User("Ivan", 35, true, new Contact("+79371345678"),
                new String[]{"unknown", "unknown"});
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

        JSONObject jsonContact = new JSONObject("{\"phone\":\"+79371345678\"}");
        List<String> list = new ArrayList<>();
        list.add("wife");
        list.add("son");
        list.add("daughter");
        JSONArray jsonFamily = new JSONArray(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", user.getName());
        jsonObject.put("age", user.getAge());
        jsonObject.put("children", user.getChildren());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("family", jsonFamily);
        System.out.println(new JSONObject(user));
        System.out.println(jsonObject);

    }
}
