package edu.project3.parsers;
//CHECKSTYLE:OFF: checkstyle:ImportOrder

import lombok.extern.slf4j.Slf4j;
import edu.project3.Statistics;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

@Slf4j
public class FilesParser extends AbstractParser {
    protected FilesParser(String path, String startDate, String endDate) {
        super(path, startDate, endDate);
    }

    @Override
    void parseResource() {
        List<String> files = fileInTimes(path);
        FileParser fileParser;
        for (String s : files) {
            fileParser = new FileParser(s);
            fileParser.parseResource();
        }
    }

    private List<String> fileInTimes(String path) {
        LocalDate from = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate to = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
        List<String> paths = new ArrayList<>();
        try {
            Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    LocalDateTime fileCreationTime =
                        LocalDateTime.ofInstant(attrs.creationTime().toInstant(), ZoneOffset.UTC);
                    LocalDate fileDate = fileCreationTime.toLocalDate();
                    if (fileDate.isBefore(from) && fileDate.isAfter(to)) {
                        paths.add(String.valueOf(file));
                    }

                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return paths;
    }
}
