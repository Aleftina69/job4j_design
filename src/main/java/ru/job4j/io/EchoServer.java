package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    output.flush();
                    String string = input.readLine();
                    if (string != null) {
                        if (string.contains("msg=Exit")) {
                            output.write("Завершить работу сервера.".getBytes());
                            output.flush();
                            break;
                        }
                        if (string.contains("msg=Hello")) {
                            output.write("Hello, dear friend.".getBytes());
                            output.flush();
                        }
                        if (string.contains("msg=Any")) {
                            output.write("What, dear friend?".getBytes());
                            output.flush();
                        }
                    }
                } catch (IOException e) {
                    LOG.error("Exception while handling client connection", e);
                }
            }
        } catch (IOException e) {
            LOG.error("Exception while starting server", e);
        }
    }
}