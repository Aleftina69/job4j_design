package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream bf = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(bf.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateArgs(ArgsName argsName) {
        File file = new File(argsName.get("d"));
        if (!file.exists()) {
            throw new IllegalArgumentException("Directory does not exist: " + argsName.get("d"));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Provided path is not a directory: " + argsName.get("d"));
        }
        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Exclude must start with a dot: " + argsName.get("e"));
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Output file must have .zip extension: " + argsName.get("o"));
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        zip.validateArgs(argsName);
        File directory = new File(argsName.get("d"));
        File target = new File(argsName.get("d") + "\\" + argsName.get("o"));
        List<Path> paths = Search.search(directory.toPath(), p -> p.toFile().getName().endsWith(argsName.get("e")));
        zip.packFiles(paths, target);
    }
}