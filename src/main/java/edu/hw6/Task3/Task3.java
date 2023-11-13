package edu.hw6.Task3;

import java.nio.file.Files;

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
        return entry -> entry.getFileName().toString().endsWith(glob);
    }

    public AbstractFilter magicNumbers(String[] strings) {
        return entry -> {
            for (String string : strings) {
                if (!entry.getFileName().toString().contains(string)) {
                    return false;
                }
            }
            return true;
        };
    }
}
