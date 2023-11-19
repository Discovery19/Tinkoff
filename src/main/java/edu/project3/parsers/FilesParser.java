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
    public FilesParser(Statistics statistics) {
        super(statistics);
    }

    @Override
    void parseResource(String path) {
        List<String> files = fileInTimes(path);
        for (String s : files) {
            FileParser fileParser = new FileParser(statistics);
            fileParser.parseResource(s);
        }
    }

    List<String> fileInTimes(String path) {
        LocalDate startDate = LocalDate.parse(statistics.getCmdParse().getFrom(), DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(statistics.getCmdParse().getTo(), DateTimeFormatter.ISO_DATE);
        List<String> paths = new ArrayList<>();
        try {
            Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    LocalDateTime fileCreationTime =
                        LocalDateTime.ofInstant(attrs.creationTime().toInstant(), ZoneOffset.UTC);
                    LocalDate fileDate = fileCreationTime.toLocalDate();
                    if (fileDate.isBefore(endDate) && fileDate.isAfter(startDate)) {
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
