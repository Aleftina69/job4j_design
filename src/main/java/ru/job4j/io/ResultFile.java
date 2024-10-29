package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("data/dataresult.txt")) {
            int[][] table = new int[10][10];
            for (int i = 0; i <= 10; i++) {
                for (int j = 0; j <= 10; j++) {
                    String line = i + " * " + j + " = " + (i * j) + "    ";
                    output.write(line.getBytes());
                }
                output.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}