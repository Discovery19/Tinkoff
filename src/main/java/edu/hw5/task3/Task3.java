package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Task3 implements Handler {
    public Task3() {
    }

    private final List<String> defaultFormats = Arrays.asList(
        "yyyy-MM-dd",
        "yyyy-MM-d",
        "dd/MM/yyyy",
        "d/M/yy",
        "d/M/yyyy"
    );

    @Override
    public LocalDate matcher(String string, DateTimeFormatter pattern) {
        return LocalDate.parse(string, pattern);
    }

    @Override
    public LocalDate getDate(String string) {
        LocalDate localDate = LocalDate.now();
        return switch (string) {
            case "tomorrow" -> localDate.plusDays(1);
            case "today" -> localDate;
            case "yesterday" -> localDate.minusDays(1);
            default -> getDateDays(string);
        };
    }

    private LocalDate getDateDays(String string) {
        LocalDate localDate = LocalDate.now();
        if (string.contains("day ago")) {
            return localDate.minusDays(1);
        } else if (string.contains("days ago")) {
            return localDate.minusDays(Long.parseLong(string.split(" ")[0]));
        }
        return null;
    }

    public Optional<LocalDate> parseDate(String string) {
        for (String format : defaultFormats) {
            try {
                return Optional.ofNullable(matcher(string, DateTimeFormatter.ofPattern(format)));
            } catch (Exception ignored) {
            }
        }
        LocalDate localDate = getDate(string);
        if (localDate != null) {
            return Optional.of(localDate);
        }
        return Optional.empty();
    }

}
