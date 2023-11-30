package edu.project3.Report;
//CHECKSTYLE:OFF: checkstyle:ImportOrder

import edu.project3.Statistics;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdocReport implements Report {
    AdocReport() {
    }

    @Override
    public void report(Statistics statistics) {
        List<Map.Entry<String, Integer>> answerList = statistics.answersStatistic();
        int logSize = statistics.sizeOfLog();
        int allRequestsNum = statistics.getRequestsNum();
        List<Map.Entry<String, Integer>> resources = statistics.getMostPopularResources();
        //CHECKSTYLE:OFF: checkstyle:MultipleStringLiterals
        try (FileWriter writer = new FileWriter("src/main/resources/result/result.adoc")) {
            //first
            writer.write("== Общая информация" + "\n");
            writer.write("|===" + "\n");
            writer.write("|        Метрика        |     Значение " + "\n");
            writer.write("|       Файл(-ы)        | " + statistics.getPath().split("\\\\")[0] + "\n");
            writer.write("|    Начальная дата     | " + statistics.getStartDate() + "\n");
            writer.write("|       Конечная дата   |" + statistics.getEndDate() + "\n");
            writer.write("|  Количество запросов  |" + allRequestsNum + "\n");
            writer.write("| Средний размер ответа |" + logSize + "\n");
            writer.write("|===" + "\n");
            //second
            writer.write("==== Запрашиваемые ресурсы" + "\n");
            writer.write("|===" + "\n");
            writer.write("|     Ресурс      | Количество " + "\n");
            for (Map.Entry<String, Integer> entry : resources) {
                writer.write("|" + entry.getKey() + "|" + entry.getValue() + "\n");
            }
            writer.write("|===" + "\n");

            writer.write("==== Коды ответа" + "\n");
            writer.write("|===" + "\n");
            writer.write("| Код |          Имя          | Количество " + "\n");
            for (Map.Entry<String, Integer> entry : answerList) {
                String name = answerName(entry.getKey());
                writer.write("|" + entry.getKey() + "|" + name + "|" + entry.getValue() + "\n");
            }
            writer.write("|===" + "\n");

            log.info("Текст успешно записан в файл result.adoc");
        } catch (IOException e) {
            log.error("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
