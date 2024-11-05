package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Директория не существует: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Это не директория: %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("Размер родительской директории: %s", file.getTotalSpace()));
        System.out.println(String.format("Имя родительской директории: %s", file.getName()));
        for (File subfile : file.listFiles()) {
            System.out.println(String.format(subfile.getName() + ":" + subfile.length()));
        }
    }
}
