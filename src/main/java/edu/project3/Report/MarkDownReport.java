package edu.project3.Report;
//CHECKSTYLE:OFF: checkstyle:ImportOrder

import lombok.extern.slf4j.Slf4j;
import edu.project3.Statistics;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class MarkDownReport implements Report {
    MarkDownReport() {

    }

    @Override
    public String report(Statistics statistics) {
        StringBuilder sb = new StringBuilder();
        List<Map.Entry<String, Integer>> answerList = statistics.answersStatistic();
        int logSize = statistics.sizeOfLog();
        int allRequestsNum = statistics.getRequestsNum();
        List<Map.Entry<String, Integer>> resources = statistics.getMostPopularResources();

        sb.append("#### Общая информация").append("\n");
        appendTableHeader(sb, "Метрика", "Значение");
        appendTable(sb, "Файл(-ы)", statistics.getPath().split("\\\\")[0]);
        appendTable(sb, "Начальная дата", statistics.getStartDate());
        appendTable(sb, "Конечная дата", statistics.getEndDate());
        appendTable(sb, "Количество запросов", allRequestsNum);
        appendTable(sb, "Средний размер ответа", logSize);

        sb.append("#### Запрашиваемые ресурсы").append("\n");
        appendTableHeader(sb, "Ресурс", "Количество");
        for (Map.Entry<String, Integer> entry : resources) {
            appendTable(sb, entry.getKey(), entry.getValue());
        }

        sb.append("#### Коды ответа").append("\n");
        appendTableHeader(sb, "Код", "Имя", "Количество");
        for (Map.Entry<String, Integer> entry : answerList) {
            String name = answerName(entry.getKey());
            appendTable(sb, entry.getKey(), name, entry.getValue());
        }
        log.info(sb.toString());
        return sb.toString();
    }

    private void appendTableHeader(StringBuilder sb, String... headers) {
        sb.append("|");
        int colums = 0;
        for (String header : headers) {
            sb.append(" ").append(header).append(" |");
            colums++;
        }
        sb.append("\n");
        for (int i=0; i<colums; i++) {
            sb.append("|:-------------:");
        }
        sb.append("|").append("\n");

    }

    private void appendTable(StringBuilder sb, Object... values) {
        sb.append("|");
        for (Object value : values) {
            sb.append(" ").append(value).append(" |");
        }
        sb.append("\n");

    }
}
