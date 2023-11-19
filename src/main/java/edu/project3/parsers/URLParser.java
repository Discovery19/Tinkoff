package edu.project3.parsers;
//CHECKSTYLE:OFF: checkstyle:ImportOrder
import lombok.extern.slf4j.Slf4j;
import edu.project3.Statistics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Slf4j
public class URLParser extends AbstractParser {

    URLParser(Statistics stats) {
        super(stats);
    }

    @Override
    void parseResource(String path) {
        try {
            var url = new URL(String.valueOf(path));
            try (var reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String request;
                int count = 0;
                //CHECKSTYLE:OFF: checkstyle:MagicNumber
                while ((request = reader.readLine()) != null && count < 10) {
                    parseRequest(request);
                    count++;
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        } catch (IOException ignored) {
        }

    }
}
