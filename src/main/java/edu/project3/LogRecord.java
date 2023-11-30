package edu.project3;

public class LogRecord {
    StringBuilder sb;

    public LogRecord() {
        sb = new StringBuilder();
    }

    public void add(String line) {
        sb.append(line);
    }

    public String getRecord() {
        return sb.toString();
    }
}
