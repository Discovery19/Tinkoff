package edu.project3.Report;

import edu.project3.Statistics;
import java.io.FileWriter;
import java.io.IOException;

public interface Report {

    String report(Statistics statistics);

    default void writeToFile(String string) {
        String prefix;
        if (string.startsWith("####")) {
            prefix = "md";
        } else {
            prefix = "adoc";
        }
        try (FileWriter writer = new FileWriter("src/main/resources/result/result." + prefix)) {
            writer.write(string);
        } catch (IOException ignored) {

        }

    }

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
