package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    boolean chatActive = true;
    boolean chatPause = false;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        List<String> answers = readPhrases();
        Random random = new Random();
        while (chatActive) {
            String user = scanner.nextLine();
            log.add(user);
            if (OUT.equals(user)) {
                chatActive = false;
                System.out.println("Пока.");
            } else if (STOP.equals(user)) {
                chatPause = true;
                System.out.println("Чат приостановлен. Введите *продолжить*, чтобы его возобновить.");
            } else if (CONTINUE.equals(user)) {
                chatPause = false;
                System.out.println("Чат возобновлен.");
            } else if (!chatPause) {
                String randomAnswer = answers.get(random.nextInt(answers.size()));
                System.out.println(randomAnswer);
                log.add(randomAnswer);
            }
        }
        saveLog(log);
        scanner.close();
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(botAnswers))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                phrases.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(path))) {
            for (String str : log) {
                bf.write(str);
                bf.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("C:\\projects\\job4j_design\\data\\logNew.txt", "C:\\projects\\job4j_design\\data\\answers.txt");
        consoleChat.run();
    }
}