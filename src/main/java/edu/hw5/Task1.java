package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Task1 {
    private Task1() {
    }

    private static final int MINUTES_IN_HOUR = 60;

    public static String countTime(List<String> times) {
        int totalMinutes = 0;
        for (String time : times) {
            totalMinutes += timer(time);
        }
        int averageMinutes = totalMinutes / times.size();
        int hours = averageMinutes / MINUTES_IN_HOUR;
        int minutes = averageMinutes % MINUTES_IN_HOUR;

        return String.format("%dч %dм", hours, minutes);
    }

    public static int timer(String time) {
        String[] times = time.split(" - ");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        LocalDateTime start = LocalDateTime.parse(times[0], dtf);
        LocalDateTime end = LocalDateTime.parse(times[1], dtf);
        Duration duration = Duration.between(start, end);
        return (int) duration.toMinutes();
    }
}
