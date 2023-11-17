package edu.project3.parsers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Statistics {
    //Подсчитывать общее количество запросов
    public int getRequestsNum() {
        return AbstractParser.getSize_list().size();
    }

    //Определять наиболее часто запрашиваемые ресурсы
    public List<Map.Entry<String, Integer>> getMostPopularResourses() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(AbstractParser.getResourse_map().entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        List<Map.Entry<String, Integer>> result = new ArrayList<>();
        int i = 0;
        while (i < list.size() && i < 3) {
            result.add(list.get(i));
            i++;
        }
        return result;
    }

    //Определять наиболее часто встречающиеся коды ответа
    public List<Map.Entry<String, Integer>> answersStatistic() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(AbstractParser.getAnswer_map().entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        List<Map.Entry<String, Integer>> result = new ArrayList<>();
        int i = 0;
        while (i < list.size() && i < 3) {
            result.add(list.get(i));
            i++;
        }
        return result;
    }

    //размер лога в байтах
    public int sizeOfLog() {
        int sum = 0;
        for (int i : AbstractParser.getSize_list()) {
            sum += i;
        }
        if (AbstractParser.getSize_list().size()==0){
            return 0;
        }
        return sum / AbstractParser.getSize_list().size();
    }
}

