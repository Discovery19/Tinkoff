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
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader reader;

    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 8080;

    public void connect() {
        try {
            new Thread(this::runClient).start();
        } catch (Exception e) {
            log.error("Error while starting clients");
        }
    }

    private void runClient() {
        try {
            socket = new Socket(SERVER_ADDRESS, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(System.in));
            log.info("Клиент подключен к серверу. Введите сообщения (для выхода введите 'exit'):");

            String userInput;
            //CHECKSTYLE:OFF: checkstyle:InnerAssignment
            while (!(userInput = reader.readLine()).equals("exit")) {
                out.write(userInput + "\n");
                out.flush();

                String serverResponse = in.readLine();
                log.info("Ответ сервера: " + serverResponse);
            }
            log.info("Клиент вышел");
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error("Error while reading");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                log.error("Ошибка при закрытии сокета");
            }
            Thread.currentThread().interrupt();
        }
    }
//    public void closeClientTests(){
//        try {
//            socket.close();
//            in.close();
//            out.close();
//            reader.close();
//        } catch (IOException e) {
//            log.error("Ошибка при закрытии сокета");
//        }
//    }
}

