package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        List<String> filters = List.of(filter.split(","));
        List<Integer> indices = new ArrayList<>();
        String[] tableHeaders;
        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            if (scanner.hasNextLine()) {
                String tableHeader = scanner.nextLine();
                tableHeaders = tableHeader.split(delimiter);
                for (String column : filters) {
                    int index = -1;
                    for (int i = 0; i < tableHeaders.length; i++) {
                        if (tableHeaders[i].equals(column)) {
                            index = i;
                            break;
                        }
                    }
                    if (index == -1) {
                        throw new IllegalArgumentException();
                    }
                    indices.add(index);
                }
                PrintStream printStream;
                if ("stdout".equals(out)) {
                    printStream = System.out;
                } else {
                    printStream = new PrintStream(new FileOutputStream(out));
                }
                StringJoiner headerJoiner = new StringJoiner(delimiter);
                for (Integer index : indices) {
                    headerJoiner.add(tableHeaders[index]);
                }
                printStream.println(headerJoiner);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] values = line.split(delimiter);
                    StringJoiner joiner = new StringJoiner(delimiter);
                    for (Integer index : indices) {
                        joiner.add(values[index]);
                    }
                    printStream.println(joiner);
                }
                if (!"stdout".equals(out)) {
                    printStream.close();
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException();
        }
        ArgsName argsName = ArgsName.of(args);
        try {
            handle(argsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}