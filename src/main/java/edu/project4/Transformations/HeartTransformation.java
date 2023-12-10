package edu.project4.Transformations;

public class HeartTransformation extends Transformations {
    public HeartTransformation(int red, int green, int blue) {

    }

    public HeartTransformation() {

    }

    @Override
    public double transformX(double x, double y) {
        double sqrt = Math.sqrt(x * x + y * y);
        return sqrt * Math.sin(sqrt * Math.atan(y / x));
    }

    @Override
    public double transformY(double x, double y) {
        double sqrt = Math.sqrt(x * x + y * y);
        return -sqrt * Math.cos(sqrt * Math.atan(y / x));
    }
}
