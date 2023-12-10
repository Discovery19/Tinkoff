package edu.project4.Transformations;

import java.util.Random;
import lombok.Getter;
//CHECKSTYLE:OFF: checkstyle:MagicNumber
public abstract class Transformations {
    Random random = new Random();
    @Getter int red, green, blue;

    protected Transformations() {
        this.red = random.nextInt(0, 255);
        this.green = random.nextInt(0, 255);
        this.blue = random.nextInt(0, 255);
    }

    protected Transformations(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public abstract double transformX(double x, double y);

    public abstract double transformY(double x, double y);

    double radius(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    double radiusSquared(double x, double y) {
        return x * x + y * y;
    }

    double theta(double x, double y) {
        return Math.atan(x / y);
    }

    double phi(double x, double y) {
        return Math.atan(y / x);
    }
}
