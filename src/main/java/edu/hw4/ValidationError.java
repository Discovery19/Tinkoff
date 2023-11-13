package edu.hw4;

public class ValidationError extends Exception {
    public ValidationError(String errorType) {
        super(errorType);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o1) {
        return o1 instanceof ValidationError;
    }
}
