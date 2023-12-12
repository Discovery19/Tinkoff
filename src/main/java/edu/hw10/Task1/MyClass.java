package edu.hw10.Task1;

import lombok.Getter;

public class MyClass {
    @Min(value = 0)
    @Max(value = Integer.MAX_VALUE)
    @Getter private final int intValue;

    @NotNull
    @Getter private final String stringValue;

    public MyClass(int intValue, String stringValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
    }
}
