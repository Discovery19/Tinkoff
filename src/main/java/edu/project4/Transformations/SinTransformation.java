package edu.project4.Transformations;

public class SinTransformation extends Transformations {
    public SinTransformation(int red, int green, int blue) {

    }

    public SinTransformation() {

    }

    @Override
    public double transformX(double x, double y) {
        return Math.sin(x);
    }

    @Override
    public double transformY(double x, double y) {
        return Math.sin(y);
    }
}
