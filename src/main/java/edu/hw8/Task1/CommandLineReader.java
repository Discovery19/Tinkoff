package edu.hw8.Task1;
//CHECKSTYLE:OFF: checkstyle:ImportOrder
import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class CommandLineReader {
    private CommandLineReader() {

    }

    private static BufferedReader reader;

    public static String read() {
        if (reader != null) {
            try {
                return reader.readLine();
            } catch (IOException e) {
                log.error("Error reading command line", e);
            }
        } else {
            reader = new BufferedReader(new InputStreamReader(System.in));
            read();
        }
        return "";
    }

    public static void closeReader() {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                log.error("Error while closing", e);
            }

        }
    }
}
