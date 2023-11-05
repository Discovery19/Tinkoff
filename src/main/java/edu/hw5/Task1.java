package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task1 {
    private Task1() {
    }

    private static final int HOURS_IN_A_DAY = 24;
    private static final int MINUTES_IN_HOUR = 60;

    public static String countTime(String time) {
        String[] times = time.split(" - ");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse(times[0], dtf);
        LocalDateTime end = LocalDateTime.parse(times[1], dtf);
        Duration duration = Duration.between(start, end);
        int days = (int) duration.toDays();
        int hours = (int) (duration.toHours() % HOURS_IN_A_DAY);
        int minutes = (int) (duration.toMinutes() % MINUTES_IN_HOUR);
        return days + " days " + hours + " hours " + minutes + " minutes";
    }
}
