package edu.project3.parsers;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import lombok.Getter;

public class CmdParse {
    @Getter private static String path;
    @Getter private static OffsetDateTime from;
    @Getter private static OffsetDateTime to;
    @Getter private static String format;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public CmdParse() {
    }

    public void parse(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].startsWith("--")) {
                switch (args[i]) {
                    case "--path":
                        path = args[++i];
                        break;
                    case "--from":
                        LocalDateTime localDateTimeFrom = LocalDateTime.parse(args[++i]+ "T00:00:00Z", DateTimeFormatter.ISO_DATE_TIME);
                        from = localDateTimeFrom.atOffset(ZoneOffset.UTC);
                        break;
                    case "--to":
                        System.out.println("sukak");
                        LocalDateTime localDateTimeTo = LocalDateTime.parse(args[++i]+ "T00:00:00Z", DateTimeFormatter.ISO_DATE_TIME);
                        to = localDateTimeTo.atOffset(ZoneOffset.UTC);
                        break;
                    case "--format":
                        format = args[++i];
                        break;
                    default:
                        break;
                }
            }
            if (from!=null && to==null){
                System.out.println("time");
                LocalDateTime now = LocalDateTime.now();
                System.out.println(now);
                to = now.atOffset(ZoneOffset.UTC);
                System.out.println(to);
            }
        }
    }

}
