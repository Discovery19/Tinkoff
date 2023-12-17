package edu.project3.parsers;
//CHECKSTYLE:OFF: checkstyle:ImportOrder
import edu.project3.LogRecord;
import lombok.extern.slf4j.Slf4j;
import edu.project3.Statistics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Slf4j
public class URLParser extends AbstractParser {

    protected URLParser(String path, String startDate, String endDate) {
        super(path, startDate, endDate);
    }

    @Override
    public LogRecord parseResource() {
        LogRecord logRecord = new LogRecord();
        try {
            var url = new URL(String.valueOf(path));
            try (var reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String request;
                int count = 0;
                //CHECKSTYLE:OFF: checkstyle:MagicNumber
                while ((request = reader.readLine()) != null && count < 10) {
                    parseRequest(request, logRecord);
                    count++;
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        } catch (IOException ignored) {
        }
        return logRecord;
    }
}
