package at.pwd.asciishop.app;

/**
 * Class representing an image - it is immutable by design.
 * Created by Robert on 07.11.2014.
 */
public class AsciiImage {
    private char[][] data;

    public AsciiImage(final int width, final int height) {
        this.data = new char[width][height];

    }

    public AsciiImage(final AsciiImage image) {
        this.data = new char[image.getWidth()][];
        for (int i = 0; i < image.data.length; i++) {
            this.data[i] = image.data[i].clone();
        }

    }

    /**
     * @return If w is not yet set properly '-1' is returned instead
     */
    public int getWidth() {
        return data.length;
    }

    public int getHeight() {
        return data[0].length;
    }

    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int y = 0; y < getHeight(); y++) {
            if (y != 0) {
                builder.append('\n');
            }
            for (int x = 0; x < getWidth(); x++) {
                builder.append(access(x, y));
            }
        }
        return builder.toString();
    }

    /**
     * Returns the character in the image representing this instance. Invalid x/y position return '0'
     */
    public char access(final int x, final int y) {
        final int width  = getWidth();
        final int height = getHeight();

        if (x >= 0 && x < width && y >= 0 && y < height) {
            return this.data[x][y];
        } else {
            return 0;
        }
    }

    public AsciiImage set(final int x, final int y, final char newChar) {
        if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight()) {
            final AsciiImage newImage = new AsciiImage(this);
            newImage.data[x][y] = newChar;
            return newImage;
        } else {
            return this;
        }
    }
}
