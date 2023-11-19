package edu.project3.Report;

import edu.project3.Statistics;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReportHandler {
    public ReportHandler() {

    }

    public Report handler(Statistics statistics) {
        String unknown = "Unknown format";
        String format = statistics.getCmdParse().getFormat();
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
