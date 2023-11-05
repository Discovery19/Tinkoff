package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public interface Handler {
    LocalDate matcher(String string, DateTimeFormatter pattern);

    LocalDate getDate(String string);
}
