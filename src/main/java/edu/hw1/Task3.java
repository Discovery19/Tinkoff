package edu.hw1;

import java.util.Arrays;

public class Task3 {
    private Task3() {

    }

    public static boolean isNestable(int[] a1, int[] a2) {
        if (a1.length == 0 || a2.length == 0) {
            return false;
        }
        Arrays.sort(a1);
        Arrays.sort(a2);
        return a1[0] > a2[0] && a1[a1.length - 1] < a2[a2.length - 1];
    }
}
