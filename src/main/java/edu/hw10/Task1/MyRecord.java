package edu.hw10.Task1;

public record MyRecord(@Min(value = 0) @Max(value = Integer.MAX_VALUE) int intValue, @NotNull String stringValue) {
}
