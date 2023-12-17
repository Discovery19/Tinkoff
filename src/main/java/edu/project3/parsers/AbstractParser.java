package edu.project3.parsers;

import edu.project3.LogRecord;

public abstract class AbstractParser {
    String path;
    String startDate;
    String endDate;

    protected AbstractParser(String path) {
        this.path = path;
    }

    protected AbstractParser(String path, String startDate, String endDate) {
        this.path = path;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public abstract LogRecord parseResource();

    void parseRequest(String line, LogRecord logRecord) {
        logRecord.add(changeLine(line));
    }

    private String changeLine(String s) {
        return s.replace("[", "").replace("]", "").replace("\"", "") + "\n";
    }
}
