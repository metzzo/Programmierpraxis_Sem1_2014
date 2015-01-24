package at.pwd.asciishop.app;

import at.pwd.asciishop.app.operation.OperationException;

/**
 * Class representing an image - it is immutable by design.
 * Created by Robert on 07.11.2014.
 */
public class AsciiImage {
    private String charset;
    private char[][] data;

    public AsciiImage() {
        this(0,0,"");
    }

    public AsciiImage(final int width, final int height, final String charset) {
        this.data = new char[width][height];
        this.charset = charset;
    }

    public AsciiImage(final AsciiImage image) {
        this.data = new char[image.getWidth()][];
        for (int i = 0; i < image.data.length; i++) {
            this.data[i] = image.data[i].clone();
        }
        this.charset = image.charset;
    }

    public int getWidth() {
        return data.length;
    }

    public int getHeight() {
        return data[0].length;
    }

    public String getCharset() { return charset; }

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
            return '.';
        }
    }

    public AsciiImage set(final int x, final int y, final char newChar) throws OperationException.InvalidOperationException {
        if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight() && (charset.indexOf(newChar) != -1)) {
            final AsciiImage newImage = new AsciiImage(this);
            newImage.data[x][y] = newChar;
            return newImage;
        } else {
            throw new OperationException.InvalidOperationException();
        }
    }
}
