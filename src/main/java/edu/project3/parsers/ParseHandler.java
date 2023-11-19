package edu.project3.parsers;
//CHECKSTYLE:OFF: checkstyle:ImportOrder
import lombok.extern.slf4j.Slf4j;
import edu.project3.Statistics;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class ParseHandler {
    Statistics statistics;

    public ParseHandler(Statistics statistics) {
        this.statistics = statistics;

    }

    public void parse() {
        AbstractParser parser;
        File file = new File(statistics.getCmdParse().getPath());
        if (file.exists()) {
            if (file.isDirectory()) {
                parser = new FilesParser(statistics);
                parser.parseResource(statistics.getCmdParse().getPath());
            } else {
                parser = new FileParser(statistics);
                parser.parseResource(statistics.getCmdParse().getPath());
            }
        } else {
            try {
                HttpURLConnection urlConn =
                    (HttpURLConnection) new URL(statistics.getCmdParse().getPath()).openConnection();
                urlConn.connect();
                urlConn.disconnect();
                parser = new URLParser(statistics);
                parser.parseResource(statistics.getCmdParse().getPath());
            } catch (IOException ignored) {
                log.warn("Path does not exist");
            }
        }
    }

}
