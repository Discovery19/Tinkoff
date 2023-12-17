package edu.project3;

public class LogRecord {
    private final StringBuilder sb = new StringBuilder();

    public void add(String line) {
        sb.append(line);
    }

    public String getRecord() {
        return sb.toString();
    }

    public void join(LogRecord record) {
        sb.append(record.getRecord());
    }
}
