package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        Predicate<Path> condition = path -> path.toFile().getName().endsWith(".txt");
        SearchFiles searcher = new SearchFiles(condition);
            Files.walkFileTree(start, searcher);
            List<Path> foundPaths = searcher.getList();
            foundPaths.forEach(System.out::println);
    }
}