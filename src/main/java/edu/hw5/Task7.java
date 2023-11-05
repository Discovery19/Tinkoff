package edu.hw5;

public class Task7 {
    private Task7() {

    }

    private static final String REGEX_FIRST_CONDITION = "(0|1)(0|1)0(0|1)*";
    private static final String REGEX_SECOND_CONDITION = "((0)(0|1)*(0))|((1)(0|1)*(1))";
    private static final String REGEX_THIRD_CONDITION = "(0|1){1,3}";

    public static boolean isStringValidFirstCondition(String str) {
        return str.matches(REGEX_FIRST_CONDITION);
    }

    public static boolean isStringValidSecondCondition(String str) {
        return str.matches(REGEX_SECOND_CONDITION);
    }

    public static boolean isStringValidThirdCondition(String str) {
        return str.matches(REGEX_THIRD_CONDITION);
    }
}
