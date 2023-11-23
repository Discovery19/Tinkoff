package edu.hw6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter @Slf4j
public class Task1 implements Map<String, String> {
    File file;

    public Task1(Path path) {
        this.file = new File(String.valueOf(path));
    }

//    private static final int CAPACITY = 48;
//    private final TreeMap<String, String> map = new TreeMap<>();
//
//    public void readFile(Path path) {
//        try (FileChannel channel = FileChannel.open(path)) {
//            ByteBuffer buffer = ByteBuffer.allocate(CAPACITY);
//            StringBuilder sb = new StringBuilder();
//            int bytesRead = channel.read(buffer);
//            while (bytesRead != -1) {
//                buffer.flip();
//                while (buffer.hasRemaining()) {
//                    char c = (char) buffer.get();
//                    if (c == '\n' || c == '\r') {
//                        if (!sb.isEmpty()) {
//                            map.put(sb.toString().split(":")[0], sb.toString().split(":")[1]);
//                        }
//                        sb = new StringBuilder();
//                    } else {
//                        sb.append(c);
//                    }
//
//                }
//
//                buffer.clear();
//                bytesRead = channel.read(buffer);
//            }
//        } catch (IOException e) {
//            log.warn(e.getMessage());
//        }
//
//    }
//
//    public void writeFile(Path path) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(path)))) {
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                writer.write(entry.getKey() + ":" + entry.getValue());
//            }
//        } catch (IOException e) {
//            log.warn(e.getMessage());
//        }
//    }

    private List<String> readFile() {
        List<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()) {
                list.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return list;
    }

    @Override
    public int size() {
//        int size = 0;
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
//            while (bufferedReader.ready()) {
//                bufferedReader.readLine();
//                size++;
//            }
//        } catch (IOException e) {
//            log.error(e.getMessage());
//            return 0;
//        }
        return readFile().size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        List<String> list = readFile();
        for (String k : list) {
            String str = k.split(":")[0];
            if (str.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        List<String> list = readFile();
        for (String k : list) {
            String str = k.split(":")[1];
            if (str.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String get(Object key) {
        List<String> list = readFile();
        for (String k : list) {
            String str = k.split(":")[0];
            if (str.equals(key)) {
                return k.split(":")[1];
            }
        }
        return null;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        if (containsKey(key)) {
            remove(key);
        }
        List<String> list = readFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String s : list) {
                writer.write(s + "\n");
            }
            writer.write(key + ":" + value + "\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String remove(Object key) {
        List<String> list = readFile().stream().filter(e -> !e.startsWith(key.toString())).toList();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String s : list) {
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        List<String> list = readFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String s : list) {
                writer.write(s + "\n");
            }
            for (Entry<? extends String, ? extends String> entry : m.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void clear() {
        try {
            Files.write(Path.of(file.getPath()), Collections.emptyList());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        Set<String> set = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()) {
                String k = bufferedReader.readLine().split(":")[0];
                set.add(k);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            return Collections.emptySet();
        }
        return set;
    }

    @NotNull
    @Override
    public Collection<String> values() {
        Collection<String> collection = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()) {
                String v = bufferedReader.readLine().split(":")[1];
                collection.add(v.substring(0, v.length() - 1));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            return Collections.emptyList();
        }
        return collection;
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        Set<Entry<String, String>> set = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()) {
                String[] kv = bufferedReader.readLine().split(":");
                set.add(new AbstractMap.SimpleEntry<>(kv[0], kv[1]));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
        return set;
    }
}
