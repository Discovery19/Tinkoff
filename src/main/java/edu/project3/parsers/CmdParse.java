package edu.project3.parsers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.Getter;

@Getter public class CmdParse {
    private String path;
    private String from = "-";
    private String to = "-";
    private String format;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public CmdParse() {
    }

    public void parse(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].startsWith("--")) {
                switch (args[i]) {
                    case "--path":
                        //CHECKSTYLE:OFF: checkstyle:ModifiedControlVariable
                        path = args[++i];
                        break;
                    case "--from":
                        from = String.valueOf(LocalDate.parse(args[++i], formatter));
                        break;
                    case "--to":
                        to = String.valueOf(LocalDate.parse(args[++i], formatter));
                        break;
                    case "--format":
                        format = args[++i];
                        break;
                    default:
                        break;
                }
            }
            if (!from.equals("-") && to.equals("-")) {
                to = String.valueOf(LocalDate.now());
            }
        }
    }

}
