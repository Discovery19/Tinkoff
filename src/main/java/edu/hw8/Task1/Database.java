package edu.hw8.Task1;

import java.util.Map;

public class Database {
    private Database() {
    }

    private static final Map<String, String> DATABASE = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления",
        "противники", "Чем сильнее противники, тем меньше ты понимаешь о их душе",
        "победа", "Тот, кто не борется, не побеждает",
        "мудрость", "Мудрость начинается с понимания себя"
    );

    public static boolean contains(String key) {
        return DATABASE.containsKey(key);
    }

    public static String getAnswer(String key) {
        return DATABASE.get(key);
    }
}
