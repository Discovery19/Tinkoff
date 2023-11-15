package edu.project3.parsers;

import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.nio.file.Path;

@Slf4j
public class FileParser extends AbstractParser{
    @Override
    void parseResource(Path path) {
        File file = new File(String.valueOf(path));
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
