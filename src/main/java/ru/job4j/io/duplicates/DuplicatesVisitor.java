package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> fileMap = new HashMap<>();

    public void searchDublicates(Path root) throws IOException {
        Files.walkFileTree(root, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
                fileMap.computeIfAbsent(fileProperty, d -> new ArrayList<>()).add(file);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public void printDublicates() {
        for (Map.Entry<FileProperty, List<Path>> entry : fileMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.printf("%s - %dMb%n", entry.getKey().getName(), entry.getKey().getSize());
                for (Path path : entry.getValue()) {
                    System.out.println(String.valueOf(path));
                }
            }
        }
    }

    public static void main(String[] args) {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Path startPath = Paths.get("C:\\Users\\Brux\\Desktop\\q");
        try {
            visitor.searchDublicates(startPath);
            visitor.printDublicates();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}