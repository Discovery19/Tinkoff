package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ServerService implements Runnable {
    private final Socket clientSocket;

    public ServerService(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ) {
            String input;
            while ((input = in.readLine()) != null && !input.equals("exit")) {
                log.info("Получено от клиента: " + input);
                if (Database.contains(input)) {
                    String output = Database.getAnswer(input) + "\n";
                    out.write(output);
                } else {
                    out.write("Тебе предстоит самостоятельно придумать ответ(((" + "\n");
                }
                out.flush();
            }
        } catch (IOException e) {
            log.error("Ошибка при подключении клиента");
        }
    }
}
