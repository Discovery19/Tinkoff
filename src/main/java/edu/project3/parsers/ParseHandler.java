package edu.project3.parsers;

import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;

@Slf4j
public class ParseHandler {
    public static void checkPath() {
        Path path = CmdParse.getPath();
        System.out.println(path.toString());
        File file = new File(String.valueOf(path));
        if (file.exists()) {
            FileParser fileParser = new FileParser();
            fileParser.parseResource(path);
        } else {
            try {
                HttpURLConnection urlConn = (HttpURLConnection) new URL(String.valueOf(path)).openConnection();
                urlConn.connect();
                urlConn.disconnect();
                URLParser urlParser = new URLParser();
                urlParser.parseResource(path);
            } catch (IOException ignored) {

            } finally {
                log.warn("Path does not exist");
            }
        }
    }
}
