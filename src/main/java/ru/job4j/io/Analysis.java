package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class Analysis {
    public void unavailable(String source, String target) {
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(target))) {
            String startTime = null;
            boolean status = false;
            for (String line : Files.readAllLines(Path.of(source))) {
                String[] str = line.split(" ");
                if (str.length > 1) {
                    String rslt = str[str.length - 1];
                    if ("400".equals(rslt) || "500".equals(rslt)) {
                        if (!status) {
                            startTime = str[0];
                            status = true;
                        }
                    } else {
                        if (status) {
                            printWriter.printf("%s;%s%n", startTime, str[0]);
                        }
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