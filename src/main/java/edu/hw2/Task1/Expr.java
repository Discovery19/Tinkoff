package edu.hw2.Task1;

public sealed interface Expr {
    double evaluate();

    public record Constant(int i) implements Expr {
        @Override public double evaluate() {
            return i;
        }
    }

    public record Negate(Expr i) implements Expr {
        @Override public double evaluate() {
            return -i.evaluate();
        }
    }

    public record Exponent(Expr i, Expr k) implements Expr {
        @Override public double evaluate() {
            return Math.pow(i.evaluate(), k.evaluate());
        }
    }

    public record Multiplication(Expr i, Expr k) implements Expr {
        @Override public double evaluate() {
            return i.evaluate() * k.evaluate();
        }
    }

    public record Addition(Expr i, Expr k) implements Expr {
        @Override public double evaluate() {
            return i.evaluate() + k.evaluate();
        }

    }
}
