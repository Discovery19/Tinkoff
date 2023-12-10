package edu.project4.Print;
//CHECKSTYLE:OFF: checkstyle:ImportOrder
import edu.project4.Pixel;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePrinter {
    private final Pixel[][] pixels;
    private final int xRes;
    private final int yRes;

    public ImagePrinter(Pixel[][] pixels, int xRes, int yRes) {
        this.pixels = pixels;
        this.xRes = xRes;
        this.yRes = yRes;

    }

    public void print(String name) {
        BufferedImage image = new BufferedImage(xRes, yRes, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < xRes; i++) {
            for (int j = 0; j < yRes; j++) {
                    int rgb =
                        new Color((int) pixels[i][j].getRed()
                            , (int) pixels[i][j].getGreen()
                            , (int) pixels[i][j].getBlue()).getRGB();
                    image.setRGB(i, j, rgb);
            }
        }
        saveImage(image, name);
    }

    private static void saveImage(BufferedImage image, String filename) {
        try {
            ImageIO.write(image, "png", new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
