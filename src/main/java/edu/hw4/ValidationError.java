package edu.hw4;

public class ValidationError extends Exception {
    public ValidationError(String errorType) {
        super(errorType);
    }
}
