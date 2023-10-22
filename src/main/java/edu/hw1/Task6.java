package edu.hw1;

import java.util.Arrays;

public class Task6 {
    private Task6() {
    }

    private static final int BASE = 10;
    private static final int ARRAY_LENGTH = 4;
    private static final int KAPREKAR = 6174;
    private static final int[] ARRAY = new int[ARRAY_LENGTH];
    private static final int THOUSAND = 1000;
    private static final int TENTHOUSAND = 10000;

    public static int countKaprekar(int a, int k) {
        int n = a;
        if (k == 0 && !firstCheck(a)) {
            return -1;
        }
        if (n == KAPREKAR) {
            return k;
        }
        for (int i = 0; i < ARRAY.length; i++) {
            ARRAY[i] = n % BASE;
            n /= BASE;
        }
        Arrays.sort(ARRAY);
        int num = THOUSAND;
        int q = 0;
        for (int i = 0, j = ARRAY.length - 1; i < ARRAY.length; i++, j--) {
            n += ARRAY[i] * num;
            q += ARRAY[j] * num;
            num /= BASE;

        }
        return countKaprekar(q - n, k + 1);
    }

    private static boolean firstCheck(int a) {
        int n = a;
        if (n > THOUSAND && n < TENTHOUSAND) {
            for (int i = 0; i < ARRAY.length; i++) {
                ARRAY[i] = n % BASE;
                n /= BASE;
            }
            int first = ARRAY[0];
            int c = 1;
            for (int i = 1; i < ARRAY.length; i++) {
                if (ARRAY[i] == first) {
                    c++;
                }
            }
            return c != ARRAY.length;
        }
        return false;

    }
}
