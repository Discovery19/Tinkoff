package edu.hw2.Task1;

public sealed interface Expr {
    double evaluate();

    record Constant(int i) implements Expr {
        @Override public double evaluate() {
            return i;
        }
    }

    record Negate(Expr i) implements Expr {
        @Override
        public double evaluate() {
            return -i.evaluate();
        }
    }

    record Exponent(Expr i, Expr k) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(i.evaluate(), k.evaluate());
        }
    }

    record Multiplication(Expr i, Expr k) implements Expr {
        @Override
        public double evaluate() {
            return i.evaluate() * k.evaluate();
        }
    }

    record Addition(Expr i, Expr k) implements Expr {
        @Override
        public double evaluate() {
            return i.evaluate() + k.evaluate();
        }

    }
}
