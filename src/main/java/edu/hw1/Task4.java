package edu.hw1;

public class Task4 {
    private Task4() {
    }

    public static String fixString(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length() - 1; i += 2) {
            sb.append(string.charAt(i + 1));
            sb.append(string.charAt(i));
        }
        if (string.length() % 2 > 0) {
            sb.append(string.charAt(string.length() - 1));
        }
        return sb.toString();
    }
}
