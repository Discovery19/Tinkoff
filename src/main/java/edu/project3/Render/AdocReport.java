package edu.project3.Render;

import edu.project3.parsers.CmdParse;
import edu.project3.parsers.Statistics;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AdocReport implements Report {
    AdocReport(){
    }
    @Override
    public void report() {
        Statistics statistics = new Statistics();
        List<Map.Entry<String, Integer>> answerList = statistics.answersStatistic();
        int logSize = statistics.sizeOfLog();
        int allRequestsNum = statistics.getRequestsNum();
        List<Map.Entry<String, Integer>> resources = statistics.getMostPopularResourses();
        try (FileWriter writer = new FileWriter("src/main/resources/result/result.adoc")) {
            //first
            writer.write("== Общая информация" + "\n");
            writer.write("|===" + "\n");
            writer.write("|        Метрика        |     Значение " + "\n");
            writer.write("|       Файл(-ы)        | " + CmdParse.getPath().split("\\\\")[0] + "\n");
            writer.write("|    Начальная дата     | " + (CmdParse.getFrom() == null ? "-" :
                CmdParse.getFrom()) + "\n");
            writer.write("|       Конечная дата   |" + (CmdParse.getTo() == null ? "-" :
                CmdParse.getTo()) + "\n");
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

            System.out.println("Текст успешно записан в файл result.adoc");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
