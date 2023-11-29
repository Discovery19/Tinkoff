package edu.hw8.Task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {

    private static final int PORT = 8080;
    private static final int NUM_PORTS = 5;
    private final ExecutorService threadPool = Executors.newFixedThreadPool(NUM_PORTS);
    private ServerSocket serverSocket;
    @Getter private Socket clientSocket;

    public Server() {
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            log.info("Сервер запущен");
            List<Future<?>> futures = new ArrayList<>();
            try {
                //CHECKSTYLE:OFF: checkstyle:MagicNumber
                serverSocket.setSoTimeout(5000);
                while (true) {
                    try {
                        clientSocket = serverSocket.accept();
                        log.info("Клиент подключился");
                        futures.add(threadPool.submit(new ServerService(clientSocket)));
                    } catch (SocketTimeoutException e) {
                        break;
                    }
                }
            } catch (IOException ignored) {
            }
            for (Future<?> future : futures) {
                future.get();
            }
            serverSocket.close();
            threadPool.shutdown();
        } catch (IOException | InterruptedException | ExecutionException e) {
            log.error("Ошибка с сервером");
        }
    }

//    public void closeServerTests() {
//        try {
//            serverSocket.close();
//            threadPool.shutdown();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

