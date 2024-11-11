package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
                    String string = input.readLine();
                    if (string != null) {
                        if (string.contains("msg=Exit")) {
                            output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            output.write("Завершить работу сервера.".getBytes());
                            output.flush();
                            break;
                        }
                        if (string.contains("msg=Hello")) {
                            output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            output.write("Hello, dear friend.".getBytes());
                            output.flush();
                        }
                        if (string.contains("msg=Any")) {
                            output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            output.write("What, dear friend?".getBytes());
                            output.flush();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}