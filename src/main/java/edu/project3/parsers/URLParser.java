package edu.project3.parsers;

import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;

@Slf4j
public class URLParser extends AbstractParser{
    @Override
    void parseResource(Path path) {
        try {
            var url = new URL(String.valueOf(path));
            try (var reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                while (reader.ready()) {
                    String request = reader.readLine();
                    parseRequest(request);
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        catch (IOException ignored){

        }

    }
}
