package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task3 {
    public static AbstractFilter readable = Files::isReadable;
    public static AbstractFilter writable = Files::isWritable;

    public static AbstractFilter sizeFilter(long size) {
        return entry -> Files.size(entry) > size;
    }

    public static AbstractFilter regex(String pattern) {
        return entry -> entry.getFileName().toString().matches(pattern);
    }

    public static AbstractFilter globMatching(String glob) {
        return entry -> entry.getFileName().toString().endsWith(glob);
    }

    public static AbstractFilter magicNumbers(String[] strings) {
        return entry -> {
            for (String string : strings) {
                if (!entry.getFileName().toString().contains(string)) {
                    return false;
                }
            }
            return true;
        };
    }
    public static AbstractFilter and(AbstractFilter filter){
        return new AbstractFilter() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return accept(entry) && filter.accept(entry);
            }
        };
    }
}
