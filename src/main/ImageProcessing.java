package src.main;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.*;

public class ImageProcessing {
    BufferedImage img;
    
    public ImageProcessing(int width, int height) {
    }

    public static void main(String args[]) throws IOException {
        pixelate("squiddy.jpg", 44, 25);
        pixelateAndShrink("squiddy.jpg", 44, 25); 

    }

    public static void pixelate(String fileName, int rows, int columns) throws IOException {
        File file = new File("assets/images/" + fileName);
        BufferedImage image = ImageIO.read(file);

        int height = image.getHeight();
        int width = image.getWidth();

        int[] heightsMatrix = Segmenter.segment(height, rows);
        int[] widthsMatrix = Segmenter.segment(width, columns);

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int red = 0;
        int green = 0;
        int blue = 0;

        for (int row = 0; row < rows; row++) {
            int y = (row == 0) ? 0 : Arrays.stream(heightsMatrix).limit(row).sum();
            for (int column = 0; column < columns; column++) {
                int x = (column == 0) ? 0 : Arrays.stream(widthsMatrix).limit(column).sum();
                for (int w = 0; w < widthsMatrix[column]; w++) {
                    for (int h = 0; h < heightsMatrix[row]; h++) {
                        int a = w + x;
                        int b = h + y;

                        int clr = image.getRGB(a, b);
                        red += (clr & 0x00ff0000) >> 16;
                        green += (clr & 0x0000ff00) >> 8;
                        blue += clr & 0x000000ff;
                    }
                }

                red /= heightsMatrix[row] * widthsMatrix[column];
                green /= heightsMatrix[row] * widthsMatrix[column];
                blue /= heightsMatrix[row] * widthsMatrix[column];

                for (int w = 0; w < widthsMatrix[column]; w++) {
                    for (int h = 0; h < heightsMatrix[row]; h++) {
                        int a = w + x;
                        int b = h + y;
                        img.setRGB(a, b, new Color(red, green, blue).getRGB());
                    }
                }

                red = 0;
                green = 0;
                blue = 0;
            }
        }
        ImageIO.write(img, "png", new File("assets/images/" + fileName.substring(0, fileName.lastIndexOf(".")) + ".png"));
    }

    public static void pixelateAndShrink(String fileName, int rows, int columns) throws IOException {
        File file = new File("assets/images/"+ fileName);
        BufferedImage image = ImageIO.read(file);

        int pixHeight = rows;
        int pixWidth = columns;

        int height = image.getHeight();
        int width = image.getWidth();

        int[] heightsMatrix = Segmenter.segment(height, rows);
        int[] widthsMatrix = Segmenter.segment(width, columns);

        BufferedImage img = new BufferedImage(pixWidth, pixHeight, BufferedImage.TYPE_INT_RGB);

        int red = 0;
        int green = 0;
        int blue = 0;

        for (int row = 0; row < rows; row++) {
            int y = (row == 0) ? 0 : Arrays.stream(heightsMatrix).limit(row).sum();
            for (int column = 0; column < columns; column++) {
                int x = (column == 0) ? 0 : Arrays.stream(widthsMatrix).limit(column).sum();
                for (int w = 0; w < widthsMatrix[column]; w++) {
                    for (int h = 0; h < heightsMatrix[row]; h++) {
                        int a = w + x;
                        int b = h + y;

                        int clr = image.getRGB(a, b);
                        red += (clr & 0x00ff0000) >> 16;
                        green += (clr & 0x0000ff00) >> 8;
                        blue += clr & 0x000000ff;
                    }
                }

                red /= heightsMatrix[row] * widthsMatrix[column];
                green /= heightsMatrix[row] * widthsMatrix[column];
                blue /= heightsMatrix[row] * widthsMatrix[column];

                img.setRGB(column, row, new Color(red, green, blue).getRGB());

                red = 0;
                green = 0;
                blue = 0;
            }
        }
        ImageIO.write(img, "png", new File("assets/images/pixelated" + fileName.substring(0, fileName.lastIndexOf(".")) + ".png"));
    }

    public static void printPixelColorsRGB(int row,int cols) throws IOException {
        File file = new File("assets/images/box.png");
        BufferedImage image = ImageIO.read(file);

        int clr = image.getRGB(row, cols);
        int red = (clr & 0x00ff0000) >> 16;
        int green = (clr & 0x0000ff00) >> 8;
        int blue = clr & 0x000000ff;

        System.out.println("Red Color value = " + red);
        System.out.println("Green Color value = " + green);
        System.out.println("Blue Color value = " + blue);
    }

    public static void printPixelColorsHSV(int row, int cols) throws IOException {
        File file = new File("assets/images/box.png");
        BufferedImage image = ImageIO.read(file);

        float[] hsv = ColorOps.getHSV(new Color(image.getRGB(row, cols)));

        System.out.println("Hue = " + hsv[0]);
        System.out.println("Saturation = " + hsv[1]);
        System.out.println("Value = " + hsv[2]);
    }
}