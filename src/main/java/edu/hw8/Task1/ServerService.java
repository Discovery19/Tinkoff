package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ServerService implements Runnable {
    private final Socket clientSocket;
        Map<String, String> database = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления",
        "противники", "Чем сильнее противники, тем меньше ты понимаешь о их душе",
        "победа", "Тот, кто не борется, не побеждает",
        "мудрость", "Мудрость начинается с понимания себя"
    );

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
                if (database.containsKey(input)) {
                    String output = database.get(input) + "\n";
                    out.write(output);
                } else {
                    out.write("Тебе предстоит самостоятельно придумать ответ(((" + "\n");
                }
                out.flush();
            }
        } catch (IOException e) {
            log.error("Ошибка при подключении клиента");
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                log.error("Ошибка при закрытии сокета");
            }
        }
    }
}
