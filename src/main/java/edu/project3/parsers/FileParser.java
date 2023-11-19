package edu.project3.parsers;
//CHECKSTYLE:OFF: checkstyle:ImportOrder
import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import edu.project3.Statistics;

@Slf4j
public class FileParser extends AbstractParser {
    FileParser(Statistics statistics) {
        super(statistics);
    }

    @Override
    void parseResource(String path) {
        File file = new File(path);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                String request = reader.readLine();
                parseRequest(request);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
