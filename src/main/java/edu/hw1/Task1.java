package edu.hw1;

public class Task1 {
    private Task1() {
    }

    private static final int TIME_IN_MIN = 60;

    public static int minutesToSeconds(String str) {
        String[] array = str.split(":");
        int min;
        int sec;
        try {
            min = Integer.parseInt(array[0]);
            sec = Integer.parseInt(array[1]);
        } catch (Exception e) {
            return -1;
        }

        if (sec >= TIME_IN_MIN || sec < 0) {
            return -1;
        } else {
            return min * TIME_IN_MIN + sec;
        }
    }
}
