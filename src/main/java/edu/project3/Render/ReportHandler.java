package edu.project3.Render;

import edu.project3.parsers.CmdParse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReportHandler {
    public ReportHandler() {
        String format = CmdParse.getFormat();
        if (format == null) {
            log.error("Unknown format");
        } else {
            switch (format) {
                case "markdown" -> {
                    Report report = new MarkDownReport();
                    report.report();
                }
                case "adoc" -> {
                    Report report = new AdocReport();
                    report.report();
                }
                default -> log.error("Unknown format");
            }
        }
    }
}
