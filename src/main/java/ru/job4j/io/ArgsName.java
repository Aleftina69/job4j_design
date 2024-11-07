package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String prs : args) {
            if (prs == null || !prs.startsWith("-")) {
                throw new IllegalArgumentException("Error: This argument '" + prs + "' does not start with a '-' character");
            }
            String[] part = prs.substring(1).split("=", 2);
            if (part.length != 2 || part[0].isEmpty() || part[1].isEmpty()) {
                if (part[0].isEmpty()) {
                    throw new IllegalArgumentException("Error: This argument '" + prs + "' does not contain a key");
                } else if (!prs.contains("=")) {
                    throw new IllegalArgumentException("Error: This argument '" + prs + "' does not contain an equal sign");
                } else {
                    throw new IllegalArgumentException("Error: This argument '" + prs + "' does not contain a value");
                }
            }
            if (part[0].contains("?")) {
                throw new IllegalArgumentException("Error: This argument '" + prs + "' contains invalid characters in the key");
            }
            values.put(part[0], part[1]);
        }

    }

    public static ArgsName of(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}