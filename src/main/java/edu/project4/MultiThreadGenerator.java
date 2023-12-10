package edu.project4;

import edu.project4.Print.ImagePrinter;
import edu.project4.Transformations.AffineTransformation;
import edu.project4.Transformations.PolarTransformation;
import edu.project4.Transformations.SphericTransformation;
import edu.project4.Transformations.Transformations;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

//CHECKSTYLE:OFF: checkstyle:MagicNumber
//CHECKSTYLE:OFF: checkstyle:UncommentedMain
public class MultiThreadGenerator {
    private static final double XMIN = -1.877;
    private static final double XMAX = 1.877;
    private static final double YMIN = -1;
    private static final double YMAX = 1;
    private int xRes;
    private int yRes;
    private int symmetry;
    private int it;
    private int n;
    private Transformations[] transformations;
    private Pixel[][] pixels;

    //CHECKSTYLE:OFF: checkstyle:MagicNumber
    //CHECKSTYLE:OFF: checkstyle:UncommentedMain
    private Pixel[][] generateCanvas(int xRes, int yRes) {
        pixels = new Pixel[xRes][yRes];
        for (int i = 0; i < xRes; i++) {
            for (int j = 0; j < yRes; j++) {
                pixels[i][j] = new Pixel();
            }
        }
        return pixels;
    }

    public void createRender(int n, Transformations[] transformations, int it, int xRes, int yRes, int symmetry) {
        this.n = n;
        this.transformations = transformations;
        this.xRes = xRes;
        this.yRes = yRes;
        this.symmetry = symmetry;
        this.it = it;
        this.pixels = generateCanvas(xRes, yRes);
    }

    public Pixel[][] render() throws InterruptedException {
        int nThreads = 4;
//        double ystep = (YMAX - YMIN) / nThreads;
//        for (int i = 0; i < nThreads; i++) {
//            int finalI = i;
//            executorService.execute(() -> solvePieces(YMIN + ystep * finalI, YMAX + ystep * (finalI + 1)));
//        }
//        executorService.shutdown();
//        try {
//            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
//            correction(xRes, yRes);
//
//        }
//        catch (InterruptedException e) {
//
//        }
//        finally {
//            executorService.close();
//        }
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        Callable<Void> callable = () -> {
            solvePieces();
            return null;
        };
        var tasks = Stream.generate(() -> callable).limit(nThreads).toList();
        executorService.invokeAll(tasks);
        executorService.close();
        correction();
        return pixels;

    }

    private void solvePieces() {
        Random random = ThreadLocalRandom.current();
        for (int num = 0; num < n; num++) {
            double newX = random.nextDouble(XMIN, XMAX);
            double newY = random.nextDouble(YMIN, YMAX);

            for (int step = -20; step < it; step++) {
                int i = random.nextInt(transformations.length - 1);

                double x = transformations[i].transformX(newX, newY);
                double y = transformations[i].transformY(newX, newY);

                int len = random.nextInt(0, transformations.length);

                for (int j = 1; j < len; j++) {
                    x = transformations[len - 1].transformX(x, y);
                    y = transformations[len - 1].transformY(x, y);
                }
                if (step >= 0) {
                    pointOperations(x, y, xRes, yRes, transformations[i], symmetry);
                }
            }
        }
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

    Lock lock = new ReentrantLock();

    private void createPixel(int x, int y, Transformations transformation, int xRes, int yRes) {
        lock.lock();
        try {
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
        } finally {
            lock.unlock();
        }
    }

    private void correction() {
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

    public static void main(String[] args) throws InterruptedException {
        // Параметры фрактала
        int n = 9000;
        int it = 300;
        int xRes = 1920;
        int yRes = 1080;
        int symmetry = 5;

        Transformations[] transformations =
            {
                new SphericTransformation(255, 0, 0)
                , new PolarTransformation(0, 255, 0)
                , new SphericTransformation()
                , new AffineTransformation(10.0, 0.0, 30.0, -20.0, 0.0, -40.0)};

        MultiThreadGenerator fractal = new MultiThreadGenerator();
        fractal.createRender(n, transformations, it, xRes, yRes, symmetry);
        Pixel[][] toPrint = fractal.render();
        ImagePrinter printer = new ImagePrinter(toPrint, xRes, yRes);
        printer.print("multi.png");

    }
}
