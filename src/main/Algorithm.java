package src.main;
import java.util.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.vecmath.*;

public class Algorithm {
    private static BufferedImage image = null;

    public static void main(String[] args) {
        File file = new File("assets/images/squiddy.jpg");
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        slice(120);
    }
    
    public static void algorithm() {
        // get average color of picture
            // getHue - key
            // getSaturation - mode/scale
            // getValue - tempo
        // determine slicing rules
        // get color of pixel
            // getRed - pitch
            // getGreen - duration
            // getBlue - velocity
    }

    public static void slice(int tempo) {
        List<Float> divisions = new ArrayList<Float>(Arrays.asList(1.0f, 3/4.0f, 1/2.0f, 3/8.0f, 1/3.0f, 1/4.0f, 3/16.0f, 1/6.0f, 1/8.0f, 1/12.0f, 1/16.0f));
        int sliceHeight, sliceWidth;

        float fourBeatsInMs = 240000.0f / tempo;
        float sixteenthNoteInMs = fourBeatsInMs * divisions.get(10);

        int maxNotes = (int) (30000 / sixteenthNoteInMs);
        int area = image.getHeight() * image.getWidth();

        if (area < maxNotes) {
            sliceHeight = 1;
            sliceWidth = 1;
        } else {
            int sliceSize = area / maxNotes;
            Point2i squarestPair = ((LinkedList<Point2i>) (factorPairs(sliceSize))).getLast();
            sliceHeight = squarestPair.x;
            sliceWidth = squarestPair.y;
        }
        ImageProcessing.pixelateAndShrink(image, image.getHeight() / sliceHeight, image.getWidth() / sliceWidth);
    }

    private static List<Point2i> factorPairs(int num) {
        List<Point2i> pairs = new LinkedList<Point2i>();

        for (int i = 1; i < Math.sqrt(num); i++) {
            if (num % i == 0) {
                if (num / i == i) {
                    pairs.add(new Point2i(i, i));
                } else {
                    pairs.add(new Point2i(i, num / i));
                }
            }
        }

        return pairs;
    }
}
