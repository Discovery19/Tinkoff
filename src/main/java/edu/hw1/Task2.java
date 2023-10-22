package edu.hw1;

public class Task2 {
    private Task2() {

    }

    private final static int BASE = 10;

    public static int countDigits(int a) {
        int n = a;
        int res = 1;
        while (n / BASE > 0) {
            n /= BASE;
            res++;
        }
        return res;
    }
}
