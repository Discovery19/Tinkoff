package edu.hw3;

import java.util.TreeMap;

public class Task4 {
    private static final TreeMap<Integer, String> ROMAN_MAP = new TreeMap<>();

    public Task4() {
        //CHECKSTYLE:OFF: checkstyle:magicnumber
        ROMAN_MAP.put(1000, "M");
        ROMAN_MAP.put(900, "CM");
        ROMAN_MAP.put(500, "D");
        ROMAN_MAP.put(400, "CD");
        ROMAN_MAP.put(100, "C");
        ROMAN_MAP.put(90, "XC");
        ROMAN_MAP.put(50, "L");
        ROMAN_MAP.put(40, "XL");
        ROMAN_MAP.put(10, "X");
        ROMAN_MAP.put(9, "IX");
        ROMAN_MAP.put(5, "V");
        ROMAN_MAP.put(4, "IV");
        ROMAN_MAP.put(1, "I");
    }

    public String convertToRoman(int decimal) {
        if (ROMAN_MAP.containsKey(decimal)) {
            return ROMAN_MAP.get(decimal);
        }
        int floorKey = ROMAN_MAP.floorKey(decimal);
        return ROMAN_MAP.get(floorKey) + convertToRoman(decimal - floorKey);
    }
}
