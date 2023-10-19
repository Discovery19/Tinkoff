package edu.hw1;

public class Task5 {
    private Task5() {
    }

    private static final int BASE = 10;

    public static boolean isPalindromeDescendant(int a) {
        int n = a;
        String str = String.valueOf(n);
        StringBuilder sb = new StringBuilder(str);
        while (str.length() > 1) {
            if (str.contentEquals(sb.reverse())) {
                return true;
            } else {
                sb = sumString(str, n);
            }
            str = sb.toString();
            n = Integer.parseInt(str);
        }
        return false;
    }

    private static StringBuilder sumString(String str, int a) {
        int n = a;
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[str.length()];
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = n % BASE;
            n /= BASE;
        }
        for (int i = 0, j = 0; i < arr.length - 1; i += 2, j++) {
            arr[j] = arr[i] + arr[i + 1];
            sb.append(arr[j]);
        }
        return sb;
    }
}
