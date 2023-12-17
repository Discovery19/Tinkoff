package edu.project3.parsers;
//CHECKSTYLE:OFF: checkstyle:ImportOrder

import lombok.extern.slf4j.Slf4j;
import edu.project3.Statistics;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class ParseManager {
    String path;
    String startDate;
    String endDate;

    public ParseManager(String path, String startDate, String endDate) {
        this.path = path;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public AbstractParser getParser() {
        AbstractParser parser;
        File file = new File(path);
        if (file.exists()) {
            if (file.isDirectory()) {
                parser = new FilesParser(path, startDate, endDate);
                //parser.parseResource();
                return parser;
            } else {
                parser = new FileParser(path,startDate, endDate);
                //parser.parseResource();
                return parser;
            }
        } else {
            try {
                HttpURLConnection urlConn =
                    (HttpURLConnection) new URL(path).openConnection();
                urlConn.connect();
                urlConn.disconnect();
                parser = new URLParser(path, startDate, endDate);
                //parser.parseResource();
                return parser;
            } catch (IOException ignored) {
                log.warn("Path does not exist");
            }
        }
        return null;
    }

}
