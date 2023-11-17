package edu.project3.parsers;

import lombok.Getter;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class AbstractParser {
    @Getter private static final Map<String, Integer> adress_map = new HashMap<>();
    @Getter private static final Map<String, Integer> date_map = new HashMap<>();
    @Getter private static final Map<String, Integer> resourse_map = new HashMap<>();
    @Getter private static final Map<String, Integer> request_map = new HashMap<>();
    @Getter private static final Map<String, Integer> answer_map = new HashMap<>();
    @Getter private static final List<Integer> size_list = new ArrayList<>();

    abstract void parseResource(String path);

    void parseRequest(String line) {
        String remoteAddressRegex = "\\d+\\.\\d+\\.\\d+\\.\\d+";
        String dateRegex = "\\d+/\\w+/\\d+:\\d+:\\d+:\\d+";
        String requestRegex = "GET|HEAD|POST";
        String answerRegex = "(2|3|4|5)\\d{2}";
        size_list.add(line.getBytes().length);
        String[] data = splitLine(line);
        for (int i = 0; i < data.length; i++) {

            if (data[i].matches(remoteAddressRegex)) {
                adress_map.compute(data[i], (w, prev) -> prev != null ? prev + 1 : 1);
            }

            else if (data[i].matches(dateRegex)) {
                date_map.compute(data[i], (w, prev) -> prev != null ? prev + 1 : 1);
            }

            else if (data[i].matches(requestRegex)) {
                request_map.compute(data[i], (w, prev) -> prev != null ? prev + 1 : 1);
                resourse_map.compute(data[i + 1], (w, prev) -> prev != null ? prev + 1 : 1);
            }

            else if (data[i].matches(answerRegex)) {
                answer_map.compute(data[i], (w, prev) -> prev != null ? prev + 1 : 1);
            }
        }
    }
    String[] splitLine(String s){
        return s.replace("[", "")
            .replace("]", "")
            .replace("\"", "")
            .split(" ");
    }
}
