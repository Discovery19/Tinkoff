package edu.hw6.Task3;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.PathMatcher;

public class Task3 {
    public Task3() {
    }

    public AbstractFilter readable = Files::isReadable;
    public AbstractFilter writable = Files::isWritable;

    public AbstractFilter sizeFilter(long size) {
        return entry -> Files.size(entry) > size;
    }

    public AbstractFilter regex(String pattern) {
        return entry -> entry.getFileName().toString().matches(pattern);
    }

    public AbstractFilter globMatching(String glob) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
        return entry -> pathMatcher.matches(entry.getFileName());
    }

    public static AbstractFilter magicNumbers(int... values) {
        return entry -> {
            try (FileChannel channel = FileChannel.open(entry)) {
                ByteBuffer buffer = ByteBuffer.allocate(values.length);
                int bytesRead = channel.read(buffer);
                int valPointer = 0;
                while (bytesRead != -1) {
                    buffer.flip();
                    while (buffer.hasRemaining() && valPointer != values.length) {
                        if (buffer.get() != (byte) values[valPointer++]) {
                            return false;
                        }
                    }
                    buffer.clear();
                    bytesRead = channel.read(buffer);
                }
                return valPointer == values.length;
            }

        };
    }
}
