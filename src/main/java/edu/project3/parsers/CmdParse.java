package edu.project3.parsers;

import lombok.Getter;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;

public class CmdParse {
    @Getter private static Path path;
    @Getter private static Date from;
    @Getter private static Date to;
    @Getter private static String format;

    public CmdParse() {
    }

    public static void parse(String[] args) {
        System.out.println("it work");
        System.out.println(Arrays.toString(args));
        for (int i = 0; i < args.length - 1; i++) {
            System.out.println("bl");
            if (args[i].startsWith("--")) {
                System.out.println("more bl");
                switch (args[i]) {
                    case "--path":
                        System.out.println("path");
                        path = Path.of(args[++i]);
                        break;
                    case "--from":
                        from = new Date(Long.parseLong(args[++i]));
                        break;
                    case "--to":
                        to = new Date(Long.parseLong(args[++i]));
                    case "--format":
                        format = args[++i];
                    default:
                        break;
                }
            }
        }
    }

}
