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
    void parseResource(String path) {
        try {
            var url = new URL(String.valueOf(path));
            try (var reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                System.out.println("Loading");
                String request;
                int count = 0;
                while ((request = reader.readLine()) != null && count <10) {
                    parseRequest(request);
                    count++;
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        catch (IOException ignored){

        }

    }
}
