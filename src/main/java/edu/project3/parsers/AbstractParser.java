package edu.project3.parsers;

import edu.project3.Statistics;

public abstract class AbstractParser {
    Statistics statistics;

    protected AbstractParser(Statistics statistics) {
        this.statistics = statistics;
    }

    abstract void parseResource(String path);

    void parseRequest(String line) {
        String remoteAddressRegex = "\\d+\\.\\d+\\.\\d+\\.\\d+";
        String dateRegex = "\\d+/\\w+/\\d+:\\d+:\\d+:\\d+";
        String requestRegex = "GET|HEAD|POST";
        String answerRegex = "(2|3|4|5)\\d{2}";
        statistics.getSizeList().add(line.getBytes().length);
        String[] data = splitLine(line);
        for (int i = 0; i < data.length; i++) {
            if (data[i].matches(remoteAddressRegex)) {

                statistics.getAddressMap().compute(data[i], (w, prev) -> prev != null ? prev + 1 : 1);
            } else if (data[i].matches(dateRegex)) {
                statistics.getDateMap().compute(data[i], (w, prev) -> prev != null ? prev + 1 : 1);
            } else if (data[i].matches(requestRegex)) {
                statistics.getRequestMap().compute(data[i], (w, prev) -> prev != null ? prev + 1 : 1);
                statistics.getResourceMap().compute(data[i + 1], (w, prev) -> prev != null ? prev + 1 : 1);
            } else if (data[i].matches(answerRegex)) {
                statistics.getAnswerMap().compute(data[i], (w, prev) -> prev != null ? prev + 1 : 1);
            }
        }
    }

    String[] splitLine(String s) {
        return s.replace("[", "").replace("]", "").replace("\"", "").split(" ");
    }
}
