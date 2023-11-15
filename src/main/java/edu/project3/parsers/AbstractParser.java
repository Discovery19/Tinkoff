package edu.project3.parsers;

import lombok.Getter;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractParser {
    @Getter private static final Map<String, Integer> adress_map = new HashMap<>();
    @Getter private static final Map<String, Integer> date_map = new HashMap<>();
    @Getter private static final Map<String, Integer> resourse_map = new HashMap<>();
    @Getter private static final Map<String, Integer> request_map = new HashMap<>();
    @Getter private static final Map<String, Integer> answer_map = new HashMap<>();
    String[] checks = {"\\d+\\.\\d+\\.\\d+\\.\\d+"
        , "\\d+/\\w+/\\d+:\\d+:\\d+:\\d+"
        , "GET|HEAD|POST"
        , "\\d{3}"};
    String remote_address_regex = "\\d+\\.\\d+\\.\\d+\\.\\d+";
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("[MM/dd/yyyy:hh:mm:ss]");
    String date_regex = "\\d+/\\w+/\\d+:\\d+:\\d+:\\d+";
    String request_regex = "GET|HEAD|POST";
    String answer_regex = "202|404|500";

    abstract void parseResource(Path path);

    void parseRequest(String line) {
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
