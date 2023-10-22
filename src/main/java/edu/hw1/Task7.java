package edu.hw1;

import java.util.ArrayList;
import java.util.List;

public class Task7 {
    private Task7() {
    }

    private static final int BASE = 10;

    public static int rotateLeft(int n, int b) {
        int k = b;
        String bin = toBinary2(n);
        List<String> list = new ArrayList<>(List.of(bin.split("")));
        while (k > 0) {
            list.add(list.get(0));
            list.remove(0);
            k--;
        }
        return toDec(list);
    }

    public static int rotateRight(int n, int b) {
        int k = b;
        String bin = toBinary2(n);
        List<String> list = new ArrayList<>(List.of(bin.split("")));
        while (k > 0) {
            list.add(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);
            k--;
        }
        return toDec(list);
    }

    private static int toBinary(int a) {
        int n = a;
        int res = 0;
        int k = 1;
        while (n > 0) {
            res += (n % 2) * k;
            k *= BASE;
            n /= 2;
        }
        return res;
    }

    private static String toBinary2(int a) {
        int n = a;
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % 2);
            n /= 2;
        }
        return sb.reverse().toString();
    }

    private static int toDec(List<String> list) {
        int res = 0;
        for (int i = list.size() - 1, k = 0; i >= 0; i--, k++) {
            res += (int) (Integer.parseInt(list.get(i)) * Math.pow(2, k));
        }
        return res;
    }
}
