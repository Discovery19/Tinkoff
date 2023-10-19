package edu.hw3;

public class Task1 {
    private Task1() {

    }

    private static final int MIN_BIG = 65;
    private static final int MAX_BIG = 90;
    private static final int MIN_SMALL = 97;
    private static final int MAX_SMALL = 122;

    public static String аtBash(String string) {
        char[] array = string.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= MIN_BIG && array[i] <= MAX_BIG) {
                array[i] = (char) (MIN_BIG + MAX_BIG - array[i]);
            }
            if (array[i] >= MIN_SMALL && array[i] <= MAX_SMALL) {
                array[i] = (char) (MIN_SMALL + MAX_SMALL - array[i]);
            }
        }
        return new String(array);
    }

}
