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
    //statistics

    //    String[] checks = {"\\d+\\.\\d+\\.\\d+\\.\\d+"
//        , "\\d+/\\w+/\\d+:\\d+:\\d+:\\d+"
//        , "GET|HEAD|POST"
//        , "\\d{3}"};
//    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("[MM/dd/yyyy:hh:mm:ss]");

    String remote_address_regex = "\\d+\\.\\d+\\.\\d+\\.\\d+";
    String date_regex = "\\d+/\\w+/\\d+:\\d+:\\d+:\\d+";
    String request_regex = "GET|HEAD|POST";
    String answer_regex = "202|404|500";

    abstract void parseResource(Path path);

    void parseRequest(String line) {
        size_list.add(line.getBytes().length);
        String[] data = line.replace("[", "")
            .replace("]", "")
            .replace("\"", "")
            .split(" ");
        for (int i = 0; i < data.length; i++) {
            if (data[i].matches(remote_address_regex)) {
                adress_map.compute(data[i], (w, prev) -> prev != null ? prev + 1 : 1);
                continue;
            }
            if (data[i].matches(date_regex)) {
                date_map.compute(data[i], (w, prev) -> prev != null ? prev + 1 : 1);
                continue;
            }
            if (data[i].matches(request_regex)) {
                request_map.compute(data[i], (w, prev) -> prev != null ? prev + 1 : 1);
                resourse_map.compute(data[i + 1], (w, prev) -> prev != null ? prev + 1 : 1);
                continue;
            }
            if (data[i].matches(answer_regex)) {
                answer_map.compute(data[i], (w, prev) -> prev != null ? prev + 1 : 1);
            }
        }
    }
}
class StatisticComparator implements Comparator<String> {
    public StatisticComparator(SortedMap<String, Integer> treeMap) {
        this.treeMap = treeMap;
    }

    public
    SortedMap<String, Integer> treeMap;

    @Override
    public int compare(String o1, String o2) {
        if (treeMap.get(o1) >= treeMap.get(o2)) {
            return -1;
        } else {
            return 1;
        }
    }
}
