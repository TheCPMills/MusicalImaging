package src.main;

import java.awt.Color;
import java.util.Arrays;

public class ColorOps {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(RGBtoHSV(255, 0, 0)));
        System.out.println(Arrays.toString(HSVtoRGB(360, 100, 100)));
    }

    public static float getAlpha(Color color) {
        return color.getAlpha() / 255.0f;
    }

    public static int[] getRGB(Color color) {
        int clr = color.getRGB();
        return new int[]{((clr & 0x00FF0000) >> 16), ((clr & 0x0000ff00) >> 8), (clr & 0x000000ff)};
    }

    public static float[] getHSV(Color color) {
        return RGBtoHSV(getRGB(color));
    }

    public static float[] RGBtoHSV(int red, int green, int blue) {
        float hsv[] = new float[3];
        
        float r = red / 255.0f;
        float g = green / 255.0f;
        float b = blue / 255.0f;

        float min = Math.min(Math.min(r, g), b);
        float max = Math.max(Math.max(r, g), b);
        float delta = max - min;

        if (delta == 0) {
            hsv[0] = 0;
        } else if (max == r) {
            hsv[0] = (int) ((60 * (floorMod(((g - b) / delta), 6))) + 0.5);
        } else if (max == g) {
            hsv[0] = (int) ((60 * (((b - r) / delta) + 2)) + 0.5);
        } else {
            hsv[0] = (int) ((60 * (((r - g) / delta) + 4)) + 0.5);
        }

        hsv[1] = (max == 0) ? 0 : ((int) ((delta / max * 1000) + 0.5)) / 10.0f;

        hsv[2] = ((int) ((max * 1000) + 0.5)) / 10.0f;

        return hsv;
    }

    public static float[] RGBtoHSV(int[] rgb) {
        return RGBtoHSV(rgb[0], rgb[1], rgb[2]);
    }

    public static int[] HSVtoRGB(int hue, float saturation, float value) {
        int[] rgb = new int[3];
        float r, g, b;
        
        saturation /= 100.0;
        value /= 100.0;

        float c = saturation * value;
        float x = c * (1 -  Math.abs(((int) hue / 60) % 2 - 1));
        float m = value - c;

        if (hue > 0 && hue < 60) {
            r = c;
            g = x;
            b = 0;
        } else if (hue >= 60 && hue < 120) {
            r = x;
            g = c;
            b = 0;
        } else if (hue >= 120 && hue < 180) {
            r = 0;
            g = c;
            b = x;
        } else if (hue >= 180 && hue < 240) {
            r = 0;
            g = x;
            b = c;
        } else if (hue >= 240 && hue < 300) {
            r = x;
            g = 0;
            b = c;
        } else {
            r = c;
            g = 0;
            b = x;
        }

        rgb[0] = (int) (((r + m) * 255) + 0.5);
        rgb[1] = (int) (((g + m) * 255) + 0.5);
        rgb[2] = (int) (((b + m) * 255) + 0.5);

        return rgb;
    }

    public static int[] HSVtoRGB(float[] hsv) {
        return HSVtoRGB(((int) (hsv[0] + 0.5)), hsv[1], hsv[2]);
    }

    private static float floorMod(float a, float b) {
        float result = a % b;
        return (result >= 0) ? result : result + b;
    }
}