package at.pwd.asciishop.app;

/**
 * Class representing an image - it is immutable by design.
 * Created by Robert on 07.11.2014.
 */
public class AsciiImage {
    private String[] rows;

    public AsciiImage(final int capacity) {
        this.rows = new String[capacity];
    }
    public AsciiImage(final int width, final int height) {
        this.rows = new String[height];
        for (int i = 0; i < height; i++) {
            final StringBuilder builder = new StringBuilder();
            for (int j = 0; j < width; j++) {
                builder.append(' ');
            }
            this.rows[i] = builder.toString();
        }
    }

    public AsciiImage(final String[] rows) {
        this.rows = rows;
    }

    public AsciiImage(final AsciiImage image) {
        this.rows = image.rows;
    }

    /**
     * @return If w is not yet set properly '-1' is returned instead
     */
    public int getWidth() {
        for (final String row : rows) {
            if (row != null) {
                return row.length();
            }
        }
        return -1;
    }

    public int getHeight() {
        return rows.length;
    }

    public String getRow(final int line) {
        return rows[line];
    }

    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int y = 0; y < getHeight(); y++) {
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
        final int width  = getWidth();
        final int height = getHeight();

        if (x >= 0 && x < width && y >= 0 && y < height) {
            return this.rows[y].charAt(x);
        } else {
            return 0;
        }
    }

    public AsciiImage set(final int x, final int y, final char newChar) {
        final char oldChar = access(x, y);
        if (oldChar != 0 && oldChar != newChar) {
            final String line = this.rows[y];
            final String newLine = line.substring(0, x) + newChar + line.substring(x + 1);
            return updateRow(y, newLine);
        } else {
            return this;
        }
    }

    /**
     * Creates a new image with updated data
     * @return The new image or old, if updating was not possible due to dimensional constraints
     */
    public AsciiImage updateRow(final int row, final String data) {
        final int width  = getWidth();
        final int height = getHeight();

        if (row >= 0 && row < height) {
            if (width == -1 || width == data.length()) {
                final AsciiImage newImage = new AsciiImage(this.rows.length);
                for (int i = 0; i < this.rows.length; i++) {
                    newImage.rows[i] = (i == row) ? data : this.rows[i];
                }
                return newImage;
            }
        }

        return this;
    }
}
