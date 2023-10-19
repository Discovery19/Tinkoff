package edu.hw3;

import static java.lang.String.join;
import static java.util.Collections.nCopies;

public class Task4 {
    private Task4() {
    }

    public static String convertToRoman(int number) {
        return join("", nCopies(number, "I"))
            .replace("IIIII", "V")
            .replace("IIII", "IV")
            .replace("VV", "X")
            .replace("VIV", "IX")
            .replace("XXXXX", "L")
            .replace("XXXX", "XL")
            .replace("LL", "C")
            .replace("LXL", "XC")
            .replace("CCCCC", "D")
            .replace("CCCC", "CD")
            .replace("DD", "M")
            .replace("DCD", "CM");
    }
}
