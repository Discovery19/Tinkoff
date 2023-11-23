package edu.hw8;

import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

@Slf4j
public class Server extends Thread {
    private static final int PORT = 8080;
    List<String> serverData = List.of("qwerty", "asdfg");
    private static Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter out;

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
        Client client1 = new Client();
        client1.connect(PORT);
    }

    void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен");
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Клиент подключился");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

                String word = in.readLine();
                while (word != null && !word.equals("exit")) {
                    System.out.println(word);
                    out.flush();
                    word = in.readLine();
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }
}
