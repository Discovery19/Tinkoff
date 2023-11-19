package edu.project3.Report;

import edu.project3.Statistics;

public interface Report {

    void report(Statistics statistics);

    //CHECKSTYLE:OFF: checkstyle:ReturnCount
    default String answerName(String key) {
        switch (key) {
            case "200 " -> {
                return "OK";
            }
            case "304" -> {
                return "Redirection";
            }
            case "404" -> {
                return "Not Found ";
            }
            case "500" -> {
                return "Internal Server Error";
            }
            default -> {
                return "Unknown";
            }
        }
    }
}
