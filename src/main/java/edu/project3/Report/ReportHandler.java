package edu.project3.Report;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReportHandler {
    private final String format;

    public ReportHandler(String format) {
        this.format = format;
    }

    public Report createReport() {
        String unknown = "Unknown format";
        if (format == null) {
            log.error(unknown);
        } else {
            switch (format) {
                case "markdown" -> {
                    return new MarkDownReport();
                }
                case "adoc" -> {
                    return new AdocReport();
                }
                default -> log.error(unknown);
            }
        }
        return null;
    }
}
