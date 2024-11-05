package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Analysis {
    public void unavailable(String source, String target) {
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(target))) {
            String startTime = null;
            for (String line : Files.readAllLines(Path.of(source))) {
                String[] str = line.split(" ");
                    String status = str[0];
                    String time = str[1];
                    if ("400".equals(status) || "500".equals(status)) {
                        if (startTime == null) {
                            startTime = time;
                        }
                    } else {
                        if (startTime != null) {
                            printWriter.printf("%s;%s%n", startTime, time);
                            startTime = null;
                        }
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}