package edu.project3.parsers;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParseHandler {
    public void checkPath() {
        String path = CmdParse.getPath();
        File file = new File(path);
        if (file.exists()) {
            if (file.isDirectory()) {
                List<String> files = fileInTimes(path);
                for (String s : files) {
                    FileParser fileParser = new FileParser();
                    fileParser.parseResource(s);
                }
            }
            else {
                FileParser fileParser = new FileParser();
                fileParser.parseResource(path);
            }
        } else {
            try {
                HttpURLConnection urlConn = (HttpURLConnection) new URL(path).openConnection();
                urlConn.connect();
                urlConn.disconnect();
                URLParser urlParser = new URLParser();
                urlParser.parseResource(path);
            } catch (IOException ignored) {
                log.warn("Path does not exist");
            }
        }
    }

    List<String> fileInTimes(String path) {
        long from = CmdParse.getFrom().toInstant().toEpochMilli();
        long to = CmdParse.getTo().toInstant().toEpochMilli();
        List<String> fileList = new ArrayList<>();
        File folder = new File(String.valueOf(path));
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.lastModified() >= from && file.lastModified() <= to) {
                    fileList.add(file.getPath());
                }
            }
        }
        return fileList;
    }
}
