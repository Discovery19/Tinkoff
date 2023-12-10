package edu.project4.Transformations;

public class SphericTransformation extends Transformations {
    public SphericTransformation(int red, int green, int blue) {

    }

    public SphericTransformation() {

    }

    @Override
    public double transformX(double x, double y) {
        return x / (x * x + y * y);
    }

    @Override
    public double transformY(double x, double y) {
        return y / (x * y + y * y);
    }
}
