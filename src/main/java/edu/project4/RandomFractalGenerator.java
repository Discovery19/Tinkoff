package edu.project4;

import edu.project4.Print.ImagePrinter;
import edu.project4.Transformations.AffineTransformation;
import edu.project4.Transformations.HeartTransformation;
import edu.project4.Transformations.PolarTransformation;
import edu.project4.Transformations.SinTransformation;
import edu.project4.Transformations.SphericTransformation;
import edu.project4.Transformations.Transformations;
import java.util.Random;

//CHECKSTYLE:OFF: checkstyle:MagicNumber
//CHECKSTYLE:OFF: checkstyle:ReturnCount
//CHECKSTYLE:OFF: checkstyle:UncommentedMain
public class RandomFractalGenerator {
    private static final double XMIN = -1.777;
    private static final double XMAX = 1.777;
    private static final double YMIN = -1;
    private static final double YMAX = 1;
    private final Random random = new Random();
    private Pixel[][] pixels;
    private int xRes;
    private int yRes;
    private int symmetry;
    private int it;
    private int n;
    private int eqCount;
    private Transformations[] transformations;

    private Pixel[][] generateCanvas(int xRes, int yRes) {
        pixels = new Pixel[xRes][yRes];

        for (int i = 0; i < xRes; i++) {
            for (int j = 0; j < yRes; j++) {
                pixels[i][j] = new Pixel();
            }
        }
        return pixels;
    }

    public void createRender(int n, int eqCount, int it, int xRes, int yRes, int symmetry) {
        this.n = n;
        this.eqCount = eqCount;
        this.transformations = generateTransformations(eqCount);
        this.xRes = xRes;
        this.yRes = yRes;
        this.symmetry = symmetry;
        this.it = it;
        this.pixels = generateCanvas(xRes, yRes);
    }

    public Pixel[][] render() {
        this.pixels = generateCanvas(xRes, yRes);

        for (int num = 0; num < n; num++) {
            double newX = random.nextDouble(XMIN, XMAX);

            double newY = random.nextDouble(YMIN, YMAX);

            for (int step = -20; step < it; step++) {
                int i = random.nextInt(eqCount);

                double x = transformations[i].transformX(newX, newY);
                double y = transformations[i].transformY(newX, newY);

                int len = random.nextInt(0, transformations.length);

                for (int j = 0; j < len; j++) {
                    x = transformations[j].transformX(newX, newY);
                    y = transformations[j].transformY(newX, newY);
                }
                if (step >= 0) {
                    pointOperations(x, y, xRes, yRes, transformations[i], symmetry);
                }
            }
        }
        correction(xRes, yRes);
        return pixels;
    }

    private void pointOperations(double x, double y, int xRes, int yRes, Transformations transformation, int symmetry) {
        if (x >= XMIN && x <= XMAX && y >= YMIN && y <= YMAX) {

            double theta2 = 0;

            for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
                var xR = rotateX(x, y, theta2);
                var yR = rotateY(x, y, theta2);

                int x1 = (int) Math.round(xRes - (((XMAX - xR) / (XMAX - XMIN)) * xRes));
                int y1 = (int) Math.round(yRes - (((YMAX - yR) / (YMAX - YMIN)) * yRes));

                createPixel(x1, y1, transformation, xRes, yRes);
            }
        }
    }

    private double rotateX(double x, double y, double angle) {
        return x * Math.cos(angle) - y * Math.sin(angle);
    }

    private double rotateY(double x, double y, double angle) {
        return x * Math.sin(angle) + y * Math.cos(angle);
    }

    private void createPixel(int x, int y, Transformations transformation, int xRes, int yRes) {
        if (x < xRes && y < yRes && x > 0 && y > 0) {
            if (pixels[x][y].counter == 0) {
                pixels[x][y].red = transformation.getRed();
                pixels[x][y].green = transformation.getGreen();
                pixels[x][y].blue = transformation.getBlue();
            } else {
                pixels[x][y].red = (pixels[x][y].red + transformation.getRed()) / 2;
                pixels[x][y].green = (pixels[x][y].green + transformation.getGreen()) / 2;
                pixels[x][y].blue = (pixels[x][y].blue + transformation.getBlue()) / 2;
            }

            pixels[x][y].increment();
        }
    }

    private Transformations[] generateTransformations(int eqCount) {
        Transformations[] generated = new Transformations[eqCount];
        for (int i = 0; i < eqCount; i++) {
            generated[i] = getTransformation();
        }
        return generated;
    }

    private Transformations getTransformation() {
        int transform = random.nextInt(5);
        switch (transform) {
            case 0 -> {
                return new SinTransformation();
            }
            case 1 -> {
                return new SphericTransformation();
            }
            case 2 -> {
                return new HeartTransformation();
            }
//            case 3 -> {
//                return new DiskTransformation();
//            }
            case 3, 4 -> {
                return new PolarTransformation();
            }
            default -> {
                return new AffineTransformation();
            }
        }
    }

    private void correction(int xRes, int yRes) {
        double max = 0.0;
        double gamma = 2.2;
        for (int row = 0; row < xRes; row++) {
            for (int col = 0; col < yRes; col++) {
                if (pixels[row][col].counter != 0) {
                    pixels[row][col].normal = Math.log10(pixels[row][col].counter);
                    if (pixels[row][col].normal > max) {
                        max = pixels[row][col].normal;
                    }
                }
            }
        }
        if (max == 0.0) {
            max = 1.0;
        }
        for (int row = 0; row < xRes; row++) {
            for (int col = 0; col < yRes; col++) {
                pixels[row][col].normal /= max;
                pixels[row][col].setRed(pixels[row][col].getRed() * Math.pow(pixels[row][col].normal, (1.0 / gamma)));
                pixels[row][col].setGreen(
                    pixels[row][col].getGreen() * Math.pow(pixels[row][col].normal, (1.0 / gamma)));
                pixels[row][col].setBlue(pixels[row][col].getBlue() * Math.pow(pixels[row][col].normal, (1.0 / gamma)));
            }
        }
    }

    public static void main(String[] args) {
        // Параметры фрактала
        int n = 10000;
        int eqCount = 2;
        int it = 150;
        int xRes = 1920;
        int yRes = 1080;
        int symmetry = 12;
        RandomFractalGenerator fractal = new RandomFractalGenerator();
        fractal.createRender(n, eqCount, it, xRes, yRes, symmetry);
        ImagePrinter printer = new ImagePrinter(fractal.render(), xRes, yRes);
        printer.print("random.png");

    }
}
