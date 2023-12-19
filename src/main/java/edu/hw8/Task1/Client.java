package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {

    private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() {
        try {
            new Thread(this::runClient).start();
        } catch (Exception e) {
            log.error("Error while starting clients", e);
        }
    }

    private void runClient() {
        try (
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            log.info("Клиент подключен к серверу. Введите сообщения (для выхода введите 'exit'):");

            String userInput;
            //CHECKSTYLE:OFF: checkstyle:InnerAssignment
            while (!(userInput = CommandLineReader.read()).equals("exit")) {
                out.write(userInput + "\n");
                out.flush();

                String serverResponse = in.readLine();
                log.info("Ответ сервера: " + serverResponse);
            }
            log.info("Клиент вышел");
        } catch (IOException e) {
            log.error("Error while reading", e);
        }
    }
}

