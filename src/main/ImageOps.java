package src.main;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;

public class ImageOps {
    public static void main(String[] args) throws IOException {
        System.out.println(redestPixel("box.png"));
        System.out.println(greenestPixel("box.png"));
        System.out.println(bluestPixel("box.png"));
        System.out.println(greatestHuePixel("box.png"));
        System.out.println(mostSaturatedPixel("box.png"));
        System.out.println(brightestPixel("box.png"));
        System.out.println(mostTransparentPixel("box.png"));
    }

    public static Point redestPixel(String fileName) throws IOException {
        File file = new File("assets/images/" + fileName);
        BufferedImage image = ImageIO.read(file);

        int x = -1, y = -1;
        float maxRed = -1;

        int height = image.getHeight();
        int width = image.getWidth();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                float red = ColorOps.getRGB(new Color(image.getRGB(column, row)))[0];

                if (red > maxRed) {
                    maxRed = red;
                    x = column;
                    y = row;
                }

            }
        }
        return new Point(x, y);
    }

    public static Point greenestPixel(String fileName) throws IOException {
        File file = new File("assets/images/" + fileName);
        BufferedImage image = ImageIO.read(file);

        int x = -1, y = -1;
        float maxGreen = -1;

        int height = image.getHeight();
        int width = image.getWidth();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                float green = ColorOps.getRGB(new Color(image.getRGB(column, row)))[1];

                if (green > maxGreen) {
                    maxGreen = green;
                    x = column;
                    y = row;
                }

            }
        }
        return new Point(x, y);
    }

    public static Point bluestPixel(String fileName) throws IOException {
        File file = new File("assets/images/" + fileName);
        BufferedImage image = ImageIO.read(file);

        int x = -1, y = -1;
        float maxBlue = -1;

        int height = image.getHeight();
        int width = image.getWidth();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                float blue = ColorOps.getRGB(new Color(image.getRGB(column, row)))[2];

                if (blue > maxBlue) {
                    maxBlue = blue;
                    x = column;
                    y = row;
                }

            }
        }
        return new Point(x, y);
    }

    public static Point greatestHuePixel(String fileName) throws IOException {
        File file = new File("assets/images/" + fileName);
        BufferedImage image = ImageIO.read(file);

        int x = -1, y = -1;
        float maxHue = -1;

        int height = image.getHeight();
        int width = image.getWidth();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                float hue = ColorOps.getHSV(new Color(image.getRGB(column, row)))[0];

                if (hue > maxHue) {
                    maxHue = hue;
                    x = column;
                    y = row;
                }

            }
        }
        return new Point(x, y);
    }
    
    public static Point mostSaturatedPixel(String fileName) throws IOException {
        File file = new File("assets/images/" + fileName);
        BufferedImage image = ImageIO.read(file);

        int x = -1, y = -1;
        float maxSaturation = -1;

        int height = image.getHeight();
        int width = image.getWidth();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                float saturation = ColorOps.getHSV(new Color(image.getRGB(column, row)))[1];

                if (saturation > maxSaturation) {
                    maxSaturation = saturation;
                    x = column;
                    y = row;
                }

            }
        }
        return new Point(x, y);
    }

    public static Point brightestPixel(String fileName) throws IOException {
        File file = new File("assets/images/" + fileName);
        BufferedImage image = ImageIO.read(file);

        int x = -1, y = -1;
        float maxValue = -1;

        int height = image.getHeight();
        int width = image.getWidth();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                float value = ColorOps.getHSV(new Color(image.getRGB(column, row)))[2];

                if (value > maxValue) {
                    maxValue = value;
                    x = column;
                    y = row;
                }

            }
        }
        return new Point(x, y);
    }

    public static Point mostTransparentPixel(String fileName) throws IOException {
        File file = new File("assets/images/" + fileName);
        BufferedImage image = ImageIO.read(file);

        int x = -1, y = -1;
        float maxAlpha = -1;

        int height = image.getHeight();
        int width = image.getWidth();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                float alpha = ColorOps.getAlpha(new Color(image.getRGB(column, row)));

                if (alpha > maxAlpha) {
                    maxAlpha = alpha;
                    x = column;
                    y = row;
                }

            }
        }
        return new Point(x, y);
    }
}