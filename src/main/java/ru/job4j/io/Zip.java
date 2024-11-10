package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                if (Files.isRegularFile(source)) {
                    ZipEntry zipEntry = new ZipEntry(source.toString());
                    zipOutputStream.putNextEntry(zipEntry);
                    try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                        zipOutputStream.write(bufferedInputStream.readAllBytes());
                    }
                    zipOutputStream.closeEntry();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(bufferedInputStream.readAllBytes());
            }
            zip.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        List<Path> filesToArchive = new ArrayList<>();
        filesToArchive.add(Path.of("./job4j_design"));
        zip.packFiles(filesToArchive, new File("./archive.zip"));
    }
}