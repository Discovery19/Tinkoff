package edu.project3.Render;

public interface Report {
    void report();
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
