package edu.hw6;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter @Slf4j
public class Task1 {
    public Task1() {
    }

    private static final int CAPACITY = 48;
    private final TreeMap<String, String> map = new TreeMap<>();

    public void readFile(Path path) {
        try (FileChannel channel = FileChannel.open(path)) {
            ByteBuffer buffer = ByteBuffer.allocate(CAPACITY);
            StringBuilder sb = new StringBuilder();
            int bytesRead = channel.read(buffer);
            while (bytesRead != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    char c = (char) buffer.get();
                    if (c == '\n' || c == '\r') {
                        if (!sb.isEmpty()) {
                            map.put(sb.toString().split(":")[0], sb.toString().split(":")[1]);
                        }
                        sb = new StringBuilder();
                    } else {
                        sb.append(c);
                    }

                }

                buffer.clear();
                bytesRead = channel.read(buffer);
            }
        } catch (IOException e) {
            log.warn(e.getMessage());
        }

    }

    public void writeFile(Path path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(path)))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
            }
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
    }
}
