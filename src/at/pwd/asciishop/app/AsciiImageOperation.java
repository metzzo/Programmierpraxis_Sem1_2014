package at.pwd.asciishop.app;

import java.util.Set;
import java.util.HashSet;

/**
 * Class enables simple drawing functions like drawLabel, drawBar, ...
 * Created by Robert on 06.11.2014.
 */
public class AsciiImageOperation {
    private AsciiImage image;

    public AsciiImageOperation(final AsciiImage image) {
        this.image = image;
    }

    public AsciiImage addLine(final String line) {
        AsciiImage newImage = new AsciiImage(image.getHeight() + 1);
        int i = 0;
        for (;i < image.getHeight(); i++) {
            newImage = newImage.updateRow(i, image.getRow(i));
        }
        newImage = newImage.updateRow(i, line);
        return newImage;
    }

    /**
     * Returns how many unique characters are in the specified image
     * @return
     */
    public int getUniqueChars() {
        int uniqueChars = 0;
        final Set<Character> chars = new HashSet<Character>();
        for (int x = 0; x < this.image.getWidth(); x++) {
            for (int y = 0; y < this.image.getHeight(); y++) {
                final char currentChar = this.image.access(x, y);
                if (!chars.contains(currentChar)) {
                    uniqueChars++;
                }
            }
        }
        return uniqueChars;
    }

    /**
     * Flips the image vertically
     * @return
     */
    public AsciiImage flipV() {
        AsciiImage newImage = new AsciiImage(image);
        for (int y = 0; y < image.getHeight() / 2; y++) {
            final String topLine = image.getRow(y);
            final String bottomLine = image.getRow(image.getHeight() - y - 1);
            newImage = newImage.updateRow(y, bottomLine)
                               .updateRow(image.getHeight() - y - 1, topLine);

        }
        return newImage;
    }

    public AsciiImage transpose() {
        AsciiImage newImage = new AsciiImage(image.getWidth(), image.getHeight());
        for (int x = 0; x < newImage.getHeight(); x++) {
            for (int y = 0;  y < image.getWidth(); y++) {
                final char currChar = image.access(y, x);
                newImage = newImage.set(x, y, currChar);
            }
        }

        return newImage;
    }

    public boolean isSymmetric() {
        for (int x = 0;  x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight() / 2; y++) {
                final char left = image.access(x, y);
                final char right = image.access(image.getWidth() - x -1, y);
                if (left != right) {
                    return false;
                }
            }
        }
        return true;
    }

    public AsciiImage fill(final int x, final int y, final char newChar) {
        return fill(x, y, newChar, this.image.access(x, y));
    }

    private AsciiImage fill(final int x, final int y, final char newChar, final char oldChar) {
        // TODO: this should not be implemented recursively to avoid stack overflows
        final char currentChar = this.image.access(x, y);
        if (currentChar != 0 && currentChar != newChar && oldChar == currentChar) {
            this.image = this.image.set(x, y, newChar);
            this.image = fill(x + 1, y    , newChar, oldChar);
            this.image = fill(x - 1, y    , newChar, oldChar);
            this.image = fill(x    , y + 1, newChar, oldChar);
            this.image = fill(x    , y - 1, newChar, oldChar);
        }
        return this.image;
    }
}
