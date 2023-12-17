package edu.project3.parsers;
//CHECKSTYLE:OFF: checkstyle:ImportOrder
import edu.project3.LogRecord;
import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import edu.project3.Statistics;

@Slf4j
public class FileParser extends AbstractParser {
    protected FileParser(String path) {
        super(path);
    }
    protected FileParser(String path, String startDate, String endDate) {
        super(path, startDate, endDate);
    }
    @Override
    public LogRecord parseResource() {
        LogRecord logRecord = new LogRecord();
        File file = new File(path);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                String request = reader.readLine();
                parseRequest(request, logRecord);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return logRecord;
    }
}
