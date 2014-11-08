package at.pwd.asciishop.app;

import java.util.Arrays;

/**
 * Class representing an image - it is immutable by design.
 * Created by Robert on 07.11.2014.
 */
public class ShopImage {
    private String[] rows;

    public ShopImage(final int capacity) {
        this.rows = new String[capacity];
    }

    public ShopImage(final String[] rows) {
        this.rows = rows;
    }

    /**
     * @return If width is not yet set properly '-1' is returned instead
     */
    public int width() {
        for (final String row : rows) {
            if (row != null) {
                return row.length();
            }
        }
        return -1;
    }

    public int height() {
        return rows.length;
    }

    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int y = 0; y < height(); y++) {
            if (y != 0) {
                builder.append('\n');
            }
            builder.append(this.rows[y]);
        }
        return builder.toString();
    }

    /**
     * Returns the character in the image representing this instance. Invalid x/y position return '0'
     */
    public char access(final int x, final int y) {
        final int width  = width();
        final int height = height();

        if (x >= 0 && x < width && y >= 0 && y < height) {
            return this.rows[y].charAt(x);
        } else {
            return 0;
        }
    }

    public ShopImage set(final int x, final int y, final char newChar) {
        final char oldChar = access(x, y);
        if (oldChar != 0 && oldChar != newChar) {
            final String line = this.rows[y];
            final String newLine = line.substring(0, x) + newChar + line.substring(x + 1);
            return update(y, newLine);
        } else {
            return this;
        }
    }

    /**
     * Creates a new image with updated data
     * @return The new image or old, if updating was not possible due to dimensional constraints
     */
    public ShopImage update(final int row, final String data) {
        final int width  = width();
        final int height = height();

        if (row >= 0 && row < height) {
            if (width == -1 || width == data.length()) {
                final ShopImage newImage = new ShopImage(this.rows.length);
                for (int i = 0; i < this.rows.length; i++) {
                    newImage.rows[i] = (i == row) ? data : this.rows[i];
                }
                return newImage;
            }
        }

        return this;
    }
}
