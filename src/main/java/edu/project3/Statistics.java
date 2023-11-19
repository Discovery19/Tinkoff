package edu.project3;
//CHECKSTYLE:OFF: checkstyle:ImportOrder
import lombok.Getter;
import edu.project3.parsers.CmdParse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter public class Statistics {
    private final Map<String, Integer> addressMap = new HashMap<>();
    private final Map<String, Integer> dateMap = new HashMap<>();
    private final Map<String, Integer> resourceMap = new HashMap<>();
    private final Map<String, Integer> requestMap = new HashMap<>();
    private final Map<String, Integer> answerMap = new HashMap<>();
    private final List<Integer> sizeList = new ArrayList<>();

    CmdParse cmdParse;

    public Statistics(CmdParse cmdParse) {
        this.cmdParse = cmdParse;
    }

    public int getRequestsNum() {
        return sizeList.size();
    }
    //CHECKSTYLE:OFF: checkstyle:MagicNumber
    //Определять наиболее часто запрашиваемые ресурсы
    public List<Map.Entry<String, Integer>> getMostPopularResources() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(resourceMap.entrySet());
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
        List<Map.Entry<String, Integer>> list = new ArrayList<>(answerMap.entrySet());
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
        if (sizeList.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int i : sizeList) {
            sum += i;
        }
        return sum / sizeList.size();
    }
}


