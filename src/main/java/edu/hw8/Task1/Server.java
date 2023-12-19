package edu.hw8.Task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {

    private final int port;
    private final ExecutorService threadPool;
    private ServerSocket serverSocket;

    public Server(int port, int serverPool) {
        this.port = port;
        this.threadPool = Executors.newFixedThreadPool(serverPool);
    }

    public void startServer() throws IOException {
        serverSocket = new ServerSocket(port);
        log.info("Сервер запущен");
    }

    public void connectClients(Object... args) throws IOException {
        int i = 0;
        for (Object o : args) {
            i++;
            Client client = (Client) o;
            try {
                client.connect();
                Socket clientSocket = serverSocket.accept();
                log.info("Клиент " + i + " подключился");
                threadPool.execute(new ServerService(clientSocket));
            } catch (IOException e) {
                log.error("Ошибка подключения", e);
            }
        }
        threadPool.shutdown();
        if (threadPool.isTerminated()) {
            CommandLineReader.closeReader();
            serverSocket.close();
        }

    }
}

