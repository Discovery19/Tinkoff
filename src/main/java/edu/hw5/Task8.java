package edu.hw5;

public class Task8 {
    private Task8() {
    }

    private static final String REGEX_ODD = "^((0|1)|((0|1){2})*(0|1))$";
    private static final String REGEX_ZERO_ODD_ONE_EVEN = "^(0((0|1){2})*|1((0|1){2})*(0|1))$";
    private static final String REGEX_NO_ONE_ONE = "^(?!(0|1)*11)(0|1)*$";
    private static final String REGEX_ALL_ODD_ONE = "^(1(0|1){0,1})+$";
    private static final String REGEX_ANY_STRING_NOT_ONES = "^(?!11$|111$).*$";

    public static boolean isStringValidOdd(String str) {
        return str.matches(REGEX_ODD);
    }

    public static boolean isStringValidZeroOddOrOneEven(String str) {
        return str.matches(REGEX_ZERO_ODD_ONE_EVEN);
    }

    public static boolean isStringValidNoOneOne(String str) {
        return str.matches(REGEX_NO_ONE_ONE);
    }

    public static boolean isStringValidAllOddOne(String str) {
        return str.matches(REGEX_ALL_ODD_ONE);
    }

    public static boolean isStringValidAnyStringNotOnes(String str) {
        return str.matches(REGEX_ANY_STRING_NOT_ONES);
    }
}
