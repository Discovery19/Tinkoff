package edu.project3;
//CHECKSTYLE:OFF: checkstyle:ImportOrder

import lombok.Getter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter public class Statistics {

    private final String path;
    private final String startDate;
    private final String endDate;
    private final LogRecord logRecord;
    private final Map<String, Integer> addressMap = new HashMap<>();
    private final Map<String, Integer> dateMap = new HashMap<>();
    private final Map<String, Integer> resourceMap = new HashMap<>();
    private final Map<String, Integer> requestMap = new HashMap<>();
    //private final Map<String, Integer> answerMap = new HashMap<>();
    private final List<Integer> sizeList = new ArrayList<>();
    private final Answers answers = new Answers();

    public Statistics(String path, String startDate, String endDate, LogRecord logRecord) {
        this.path = path;
        this.startDate = startDate;
        this.endDate = endDate;
        this.logRecord = logRecord;
    }

    public void makeStatistics() {
        String remoteAddressRegex = "\\d+\\.\\d+\\.\\d+\\.\\d+";
        String dateRegex = "\\d+/\\w+/\\d+:\\d+:\\d+:\\d+";
        String requestRegex = "GET|HEAD|POST";
        String answerRegex = "(2|3|4|5)\\d{2}";

        String[] data = logRecord.getRecord().split("\n");
        for (String s : data) {
            sizeList.add(s.getBytes().length);
            String[] line = s.split(" ");
            for (int i = 0; i < line.length; i++) {
                if (line[i].matches(remoteAddressRegex)) {
                    addressMap.compute(line[i], (w, prev) -> prev != null ? prev + 1 : 1);
                } else if (line[i].matches(dateRegex)) {
                    dateMap.compute(line[i], (w, prev) -> prev != null ? prev + 1 : 1);
                } else if (line[i].matches(requestRegex)) {
                    requestMap.compute(line[i], (w, prev) -> prev != null ? prev + 1 : 1);
                    resourceMap.compute(line[i + 1], (w, prev) -> prev != null ? prev + 1 : 1);
                } else if (line[i].matches(answerRegex)) {
                    answers.add(line[i]);
                    //answerMap.compute(line[i], (w, prev) -> prev != null ? prev + 1 : 1);
                }
            }
        }
    }

    public int getRequestsNum() {
        return sizeList.size();
    }

    //CHECKSTYLE:OFF: checkstyle:MagicNumber
    //Определять наиболее часто запрашиваемые ресурсы
    public List<Map.Entry<String, Integer>> getMostPopularResources() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(resourceMap.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        List<Map.Entry<String, Integer>> result = new ArrayList<>();
        int i = 0;
        while (i < list.size() && i < 3) {
            result.add(list.get(i));
            i++;
        }
        return result;
    }

    //Определять наиболее часто встречающиеся коды ответа
    public List<Map.Entry<String, Integer>> answersStatistic() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(answers.getMap().entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        List<Map.Entry<String, Integer>> result = new ArrayList<>();
        int i = 0;
        while (i < list.size() && i < 3) {
            result.add(list.get(i));
            i++;
        }
        return result;
    }

    //размер лога в байтах
    public int sizeOfLog() {
        if (sizeList.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int i : sizeList) {
            sum += i;
        }
        return sum / sizeList.size();
    }
}


