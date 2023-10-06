package edu.hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {

    }

    public static void main(String[] args) {
        logger();
    }

    public static void logger() {
        LOGGER.info("Привет мир!");
    }

    private static final int SEC_MAX = 59;
    private static final int TIME_IN_MIN = 60;

    public static int minutesToSeconds(String str) {
        int min = Integer.parseInt(str.split(":")[0]);
        int sec = Integer.parseInt(str.split(":")[1]);
        if (sec > SEC_MAX || sec < 0) {
            return -1;
        } else {
            return min * TIME_IN_MIN + sec;
        }
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

    public static boolean isNestable(int[] a1, int[] a2) {
        Arrays.sort(a1);
        Arrays.sort(a2);
        return a1[0] > a2[0] && a1[a1.length - 1] < a2[a2.length - 1];
    }

    public static String fixString(String string) {
        StringBuilder sb = new StringBuilder();
        if (string.length() % 2 > 0) {
            for (int i = 0; i < string.length() - 1; i += 2) {
                sb.append(string.charAt(i + 1));
                sb.append(string.charAt(i));
            }
            sb.append(string.charAt(string.length() - 1));
        } else {
            for (int i = 0; i < string.length() - 1; i += 2) {
                sb.append(string.charAt(i + 1));
                sb.append(string.charAt(i));
            }
        }
        return sb.toString();
    }

    public static boolean isPalindromeDescendant(int a) {
        int n = a;
        String str = String.valueOf(n);
        StringBuilder sb = new StringBuilder(str);
        while (str.length() > 1) {
            if (str.contentEquals(sb.reverse())) {
                return true;
            } else {
                sb = new StringBuilder();
                int[] arr = new int[str.length()];
                for (int i = arr.length - 1; i >= 0; i--) {
                    arr[i] = n % BASE;
                    n /= BASE;
                }
                for (int i = 0, j = 0; i < arr.length - 1; i += 2, j++) {
                    arr[j] = arr[i] + arr[i + 1];
                    sb.append(arr[j]);
                }
            }
            str = sb.toString();
            n = Integer.parseInt(str);
        }
        return false;
    }

    private static final int ARRAY_LENGTH = 4;
    private static final int KAPREKAR = 6174;
    private static int[] array = new int[ARRAY_LENGTH];
    private static final int THOUSAND = 1000;

    public static int countK(int a, int k) {
        int n = a;
        if (n == KAPREKAR) {
            return k;
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = n % BASE;
            n /= BASE;
        }
        Arrays.sort(array);
        int num = THOUSAND;
        int q = 0;
        for (int i = 0, j = array.length - 1; i < array.length; i++, j--) {
            n += array[i] * num;
            q += array[j] * num;
            num /= BASE;

        }
        return countK(q - n, k + 1);
    }

    public static int rotateLeft(int n, int b) {
        int k = b;
        int bin = toBinary(n);
        List<String> list = new ArrayList<>(List.of(String.valueOf(bin).split("")));
        while (k != 0) {
            list.add(list.get(0));
            list.remove(0);
            k--;
        }
        return toDec(list);
    }

    public static int rotateRight(int n, int b) {
        int k = b;
        int bin = toBinary(n);
        List<String> list = new ArrayList<>(List.of(String.valueOf(bin).split("")));
        while (k != 0) {
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

    private static int toDec(List<String> list) {
        int res = 0;
        for (int i = list.size() - 1, k = 0; i >= 0; i--, k++) {
            res += (int) (Integer.parseInt(list.get(i)) * Math.pow(2, k));
        }
        return res;
    }

    public static boolean knightBoardCapture(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    if (moves(i, j, board)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static final int[] MOVE_I = {-1, 1, 2, 2, 1, -1, -2, -2};
    private static final int[] MOVE_J = {-2, -2, -1, 1, 2, 2, 1, -1};

    private static boolean moves(int i, int j, int[][] board) {
        boolean bool;
        for (int k = 0; k < MOVE_I.length; k++) {
            try {
                bool = board[i + MOVE_I[k]][j + MOVE_J[k]] == 1;
            } catch (Exception e) {
                continue;
            }
            if (bool) {
                return true;
            }
        }
        return false;
    }

}
