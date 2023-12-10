package edu.project4.Transformations;

public class PolarTransformation extends Transformations {
    public PolarTransformation(int red, int green, int blue) {

    }

    public PolarTransformation() {

    }

    public double transformX(double x, double y) {
        return Math.atan(y / x) / Math.PI;
    }

    public double transformY(double x, double y) {
        return y / (x * x + y * y) - 1;
    }
}
