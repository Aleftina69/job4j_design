package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
        Path start = Paths.get(".");
        Predicate<Path> condition = path -> path.toFile().getName().endsWith(".txt");
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(start, searcher);
        List<Path> foundPaths = searcher.getList();
        foundPaths.forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getList();
    }

    public static void validateArgs(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException();
        }
        File start = new File(args[0]);
        if (!start.exists() || !start.isDirectory()) {
            throw new IllegalArgumentException();
        }
        String extension = args[1];
        if (extension.isEmpty() || !extension.startsWith(".")) {
            throw new IllegalArgumentException();
        }
    }
}