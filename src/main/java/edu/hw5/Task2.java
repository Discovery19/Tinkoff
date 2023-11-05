package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {

    }

    private static final int DAY = 13;

    public static List<String> allBlackFridays(int year) {
        List<String> result = new ArrayList<>();
        LocalDate date = LocalDate.of(year, 1, DAY);
        while (date.getYear() < year + 1) {
            if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                result.add(date.toString());
            }
            date = date.plusMonths(1);
        }
        return result;
    }
}
