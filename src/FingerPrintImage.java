import java.awt.image.BufferedImage;

public class FingerPrintImage {
    private int width;
    private int height;
    private int[][] img;

    public FingerPrintImage(int width, int height) {
        this.width = width;
        this.height = height;
        img = new int[width][height];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setPixel(int x, int y, int color) {
        img[x][y] = color;
    }

    public int getPixel(int x, int y) {
        return img[x][y];
    }

    public static FingerPrintImage convertToGrayscale(BufferedImage image, boolean weighted) {
        FingerPrintImage grayImage = new FingerPrintImage(image.getWidth(), image.getHeight());

        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); ++y) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb & 0xFF);

                int gray;
                if (weighted) {
                    gray = (int) (0.2126 * r + 0.7152 * g + 0.0722 * b);
                } else {
                    gray = (r + g + b) / 3;
                }

                grayImage.setPixel(x, y, gray);
            }
        }
        return grayImage;
    }

    public static BufferedImage toBufferedImage(FingerPrintImage image, boolean normalized) {
        BufferedImage output = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); ++y) {
                int value = image.getPixel(x, y);
                if (normalized) {
                    value = value * 255;
                }
                int pixelRGB = (255 << 24 | value << 16 | value << 8 | value);
                output.setRGB(x, y, pixelRGB);
            }
        }
        return output;
    }
}
