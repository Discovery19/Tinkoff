package edu.project3.Report;
//CHECKSTYLE:OFF: checkstyle:ImportOrder

import edu.project3.Answers;
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
    public String report(Statistics statistics) {
        StringBuilder sb = new StringBuilder();
        List<Map.Entry<String, Integer>> answerList = statistics.answersStatistic();
        int logSize = statistics.sizeOfLog();
        int allRequestsNum = statistics.getRequestsNum();
        List<Map.Entry<String, Integer>> resources = statistics.getMostPopularResources();

        appendAdocHeader(sb, "Общая информация");
        appendAdocTable(sb, "Метрика", "Значение");
        appendAdocTableRow(sb, "Файл(-ы)", statistics.getPath().split("\\\\")[0]);
        appendAdocTableRow(sb, "Начальная дата", statistics.getStartDate());
        appendAdocTableRow(sb, "Конечная дата", statistics.getEndDate());
        appendAdocTableRow(sb, "Количество запросов", allRequestsNum);
        appendAdocTableRow(sb, "Средний размер ответа", logSize);
        appendAdocTableFooter(sb);

        appendAdocHeader(sb, "Запрашиваемые ресурсы");
        appendAdocTable(sb, "Ресурс", "Количество");
        for (Map.Entry<String, Integer> entry : resources) {
            appendAdocTableRow(sb, entry.getKey(), entry.getValue());
        }
        appendAdocTableFooter(sb);

        appendAdocHeader(sb, "Коды ответа");
        appendAdocTable(sb, "Код", "Имя", "Количество");
        for (Map.Entry<String, Integer> entry : answerList) {
            String name = answerName(entry.getKey());
            appendAdocTableRow(sb, entry.getKey(), name, entry.getValue());
        }
        appendAdocTableFooter(sb);
        log.info(sb.toString());
        return sb.toString();
    }

    private void appendAdocHeader(StringBuilder sb, String title) {
        sb.append("== ").append(title).append("\n");
    }

    private void appendAdocTable(StringBuilder sb, String... headers) {
        sb.append("|===").append("\n");
        for (String header : headers) {
            sb.append("| ").append(header);
        }
        sb.append("\n");
    }

    private void appendAdocTableRow(StringBuilder sb, Object... values) {
        for (Object value : values) {
            sb.append("|").append(value);
        }
        sb.append("\n");
    }

    private void appendAdocTableFooter(StringBuilder sb) {
        sb.append("|===");
        sb.append("\n");
    }
}
