package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
//        logger();
//        System.out.println(minutesToSeconds("13:42"));
//        System.out.println(countDigits(-1));
//        System.out.println(isNestable(new int[] {9, 9, 8}, new int[] {8, 9}));
//        System.out.println(fixString("hTsii  s aimex dpus rtni.g"));
//        System.out.println(isPalindromeDescendant(11));
//        System.out.println(isPalindromeDescendant(123657432));
//        System.out.println(isPalindromeDescendant(13001120));
//        System.out.println(isPalindromeDescendant(23336014));
//        System.out.println(countK(new int[]{3,5,2,4},0));
//        System.out.println(countK(new int[]{6,6,2,1},0));
//        System.out.println(rotateRight(8, 1));
        System.out.println(knightBoardCapture(new int[][]{
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}}
        ));
        System.out.println(knightBoardCapture(new int[][]{
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        }));
        System.out.println(knightBoardCapture(new int[][]{
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        }));
    }

    public static void logger() {
        Logger LOGGER = LogManager.getLogger();
        LOGGER.info("Привет мир!");
    }

    public static int minutesToSeconds(String str) {
        int min = Integer.parseInt(str.split(":")[0]);
        int sec = Integer.parseInt(str.split(":")[1]);
        if (sec > 59 || sec < 0) {
            return -1;
        } else {
            return min * 60 + sec;
        }
    }

    public static int countDigits(int n) {
        int res = 1;
        while (n / 10 > 0) {
            n /= 10;
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

    public static boolean isPalindromeDescendant(int n) {
        String str = String.valueOf(n);
        StringBuilder sb = new StringBuilder(str);
        while (str.length() > 1) {
            if (str.contentEquals(sb.reverse())) {
                return true;
            } else {
                sb = new StringBuilder();
                int[] array = new int[str.length()];
                for (int i = array.length - 1; i >= 0; i--) {
                    array[i] = n % 10;
                    n /= 10;
                }
                int[] arr = new int[str.length() / 2 + 1];
                for (int i = 0, j = 0; i < array.length - 1; i += 2, j++) {
                    arr[j] = array[i] + array[i + 1];
                    sb.append(arr[j]);
                }
            }
            str = sb.toString();
            n = Integer.parseInt(str);
        }
        return false;
    }

    //kall
    public static int countK(int[] n, int k) {
        int q = 0;
        for (int i = 0, j = 1000; i < n.length; i++, j /= 10) {
            q += n[i] * j;
        }
        System.out.println(q);
        if (q == 6174) {
            return k;
        }
        Arrays.sort(n);
        int[] rev = new int[4];
        for (int i = 0, j = 3; i < rev.length; i++, j--) {
            rev[i] = n[j];
        }
        for (int i = 0; i < n.length; i++) {
            rev[i] = rev[i] - n[i];
        }
        return countK(rev, k + 1);
    }

    //
    public static int rotateLeft(int n, int k) {
        int bin = toBinnary(n);
        List<String> list = new ArrayList<>(List.of(String.valueOf(bin).split("")));
        while (k != 0) {
            list.add(list.get(0));
            list.remove(0);
            k--;
        }
        return toDec(list);
    }

    public static int rotateRight(int n, int k) {
        int bin = toBinnary(n);
        List<String> list = new ArrayList<>(List.of(String.valueOf(bin).split("")));
        while (k != 0) {
            list.add(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);
            k--;
        }
        return toDec(list);
    }
    private static int toBinnary(int n) {
        int res = 0;
        int k = 1;
        while (n > 0) {
            res += (n % 2) * k;
            k *= 10;
            n /= 2;
        }
        return res;
    }
    private static int toDec(List<String> list) {
        int res=0;
        for (int i= list.size()-1, k=0;i>=0;i--,k++){
            res+= (int) (Integer.parseInt(list.get(i))*Math.pow(2,k));
        }
        return res;
    }

    //
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

    public static boolean moves(int i, int j, int[][] board) {
        int[] moveI = {-1, 1, 2, 2, 1, -1, -2, -2};
        int[] moveJ = {-2, -2, -1, 1, 2, 2, 1, -1};
        boolean bool = false;
        for (int k = 0; k < moveI.length; k++) {
            try {
                bool = board[i + moveI[k]][j + moveJ[k]] == 1;
            } catch (Exception e) {
                continue;
            }
            if (bool) {
                return bool;
            }
        }
        return bool;
    }

}
