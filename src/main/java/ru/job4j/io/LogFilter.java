package ru.job4j.io;

import javax.imageio.IIOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        try {
            return Files.lines(Path.of(file))
                    .filter(line -> {
                        String[] str = line.split(" ");
                        return str.length > 1 && "404".equals(str[str.length - 2]);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(out))) {
            for (String str : data) {
                printWriter.println(str);
            }
        } catch (IOException e) {
                e.printStackTrace();
            }
        }


    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}
