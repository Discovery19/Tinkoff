package edu.hw3;

public class Task1 {
    private Task1() {

    }

    private static final int MIN_BIG = 'A';
    private static final int MAX_BIG = 'Z';
    private static final int MIN_SMALL = 'a';
    private static final int MAX_SMALL = 'z';

    public static String atBash(String string) {
        char[] resultCharArray = string.toCharArray();
        for (int i = 0; i < resultCharArray.length; i++) {
            if (resultCharArray[i] >= MIN_BIG && resultCharArray[i] <= MAX_BIG) {
                resultCharArray[i] = (char) (MIN_BIG + MAX_BIG - resultCharArray[i]);
            }
            if (resultCharArray[i] >= MIN_SMALL && resultCharArray[i] <= MAX_SMALL) {
                resultCharArray[i] = (char) (MIN_SMALL + MAX_SMALL - resultCharArray[i]);
            }
        }
        return new String(resultCharArray);
    }

}
