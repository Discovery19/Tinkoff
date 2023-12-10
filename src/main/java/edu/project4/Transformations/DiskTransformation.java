package edu.project4.Transformations;

public class DiskTransformation extends Transformations {
    @Override
    public double transformX(double x, double y) {
        return 1.0 / Math.PI * Math.atan(y / x) * Math.sin(Math.PI * Math.sqrt(x * x + y * y));
    }

    @Override
    public double transformY(double x, double y) {
        return 1.0 / Math.PI * Math.atan(y / x) * Math.cos(Math.PI * Math.sqrt(x * x + y * y));
    }
}
