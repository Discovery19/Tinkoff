package edu.project3.parsers;

import edu.project3.LogRecord;
import lombok.Getter;

public abstract class AbstractParser {
    String path;
    String startDate;
    String endDate;
    @Getter private LogRecord logRecord;

    protected AbstractParser(String path) {
        this.path = path;
    }

    protected AbstractParser(String path, String startDate, String endDate) {
        this.path = path;
        this.startDate = startDate;
        this.endDate = endDate;
        this.logRecord = new LogRecord();

    }

    abstract void parseResource();

    void parseRequest(String line) {
        logRecord.add(changeLine(line));
    }

    private String changeLine(String s) {
        return s.replace("[", "").replace("]", "").replace("\"", "") + "\n";
    }
}
