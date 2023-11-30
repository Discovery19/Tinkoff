package edu.project3.parsers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//CHECKSTYLE:OFF: checkstyle:InnerAssignment
//CHECKSTYLE:OFF: checkstyle:ModifiedControlVariable
public record CmdArgs(String path, String from, String to, String format) {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static CmdArgs parse(String[] args) {
        String path = null;
        String from = "-";
        String to = "-";
        String format = null;

        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].startsWith("--")) {
                switch (args[i]) {

                    case "--path" -> path = args[++i];
                    case "--from" -> from = String.valueOf(LocalDate.parse(args[++i], FORMATTER));
                    case "--to" -> to = String.valueOf(LocalDate.parse(args[++i], FORMATTER));
                    case "--format" -> format = args[++i];
                    default -> {
                    }
                }
            }
            if (!from.equals("-") && to.equals("-")) {
                to = String.valueOf(LocalDate.now());
            }
        }
        //остальные проверки должны вылететь при работе программы или раньше, или чуть позже
        if (format.isEmpty() || !format.equals("markdown") && !format.equals("adoc")) {
            throw new IllegalArgumentException();
        }
        return new CmdArgs(path, from, to, format);
    }
}

