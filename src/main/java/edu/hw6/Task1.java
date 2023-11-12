package edu.hw6;

import lombok.extern.slf4j.Slf4j;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
@Slf4j
public class Task1 {
    public static void main(String[] args) {
        readFile(Path.of("src/main/resources/hw6/resourses"));
        writeFile();
    }

    private static final Map<String, String> map = new HashMap<>();

    public static void readFile(Path path) {
        try(FileChannel channel = FileChannel.open(path)){
        ByteBuffer buffer = ByteBuffer.allocate(48);
        StringBuilder sb = new StringBuilder();
        int bytesRead = channel.read(buffer);
        while (bytesRead != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                char c = (char) buffer.get();
                if (c == '\n') {
                    map.put(sb.toString().split(":")[0], sb.toString().split(":")[1]);
                    sb = new StringBuilder();
                }
                sb.append(c);
            }

            buffer.clear();
            bytesRead = channel.read(buffer);
        }}
        catch (IOException e) {
            log.warn(e.getMessage());
        }
    }
    public static void writeFile() {

        try(BufferedWriter writer= new BufferedWriter(new FileWriter("src/main/resources/hw6/fileForOutput.txt"))){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
            }
        }
        catch (IOException e){
            log.warn(e.getMessage());
        }


    }
}
