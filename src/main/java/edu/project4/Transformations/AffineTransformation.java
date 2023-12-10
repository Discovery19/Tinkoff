package edu.project4.Transformations;

public class AffineTransformation extends Transformations {
    public AffineTransformation(int red, int green, int blue) {

    }

    public AffineTransformation(double a, double b, double c, double d, double e, double f) {

    }

    public AffineTransformation() {

    }

    double a = random.nextDouble();
    double b = random.nextDouble();
    double c = random.nextDouble();
    double d = random.nextDouble();
    double e = random.nextDouble();
    double f = random.nextDouble();

    @Override
    public double transformX(double x, double y) {
        return a * x + b * y + c;
    }

    @Override
    public double transformY(double x, double y) {
        return d * x + e * y + f;
    }
}
