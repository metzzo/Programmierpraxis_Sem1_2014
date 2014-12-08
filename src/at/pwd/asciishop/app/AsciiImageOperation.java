package at.pwd.asciishop.app;

import java.util.List;

/**
 * Class enables simple drawing functions like drawLabel, drawBar, ...
 * Created by Robert on 06.11.2014.
 */
public class AsciiImageOperation {
    private AsciiImage image;

    public AsciiImageOperation(final AsciiImage image) {
        this.image = image;
    }

    public AsciiImage straighten(final char straightChar) {
        AsciiImage newImage = image;
        boolean hasDone;
        do {
            hasDone = false;
            for (int x = 0;  x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    if (image.access(x, y) == straightChar) {
                        int neighbours = 0;
                        if (image.access(x - 1, y) == straightChar) neighbours++;
                        if (image.access(x + 1, y) == straightChar) neighbours++;
                        if (image.access(x, y - 1) == straightChar) neighbours++;
                        if (image.access(x, y + 1) == straightChar) neighbours++;
                        if (neighbours <= 1) {
                            newImage = newImage.set(x, y, '.');
                            hasDone = true;
                        }
                    }
                }
            }
            image = newImage;
        } while(hasDone);
        return newImage;
    }

    public AsciiImage grow(final char growChar) {
        AsciiImage newImage = image;

        for (int x = 0;  x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (image.access(x, y) == '.') {
                    if (image.access(x - 1, y) == growChar || image.access(x + 1, y) == growChar || image.access(x, y - 1) == growChar || image.access(x, y + 1) == growChar) {
                        newImage = newImage.set(x, y, growChar);
                    }
                }
            }
        }

        return newImage;
    }

    public AsciiPoint calculateCentroid(final char findChar) {
        AsciiPoint centroid = new AsciiPoint();
        int count = 0;
        for (int x = 0;  x < image.getWidth(); x++) {
            for (int y = 0;  y < image.getHeight(); y++) {
                if (image.access(x, y) == findChar) {
                    centroid = centroid.add(new AsciiPoint(x, y));
                    count++;
                }
            }
        }

        return count > 0 ? centroid.div(count) : null;
    }

    public AsciiImage transpose() {
        AsciiImage newImage = new AsciiImage(image.getHeight(), image.getWidth());
        for (int x = 0; x < newImage.getWidth(); x++) {
            for (int y = 0;  y < newImage.getHeight(); y++) {
                final char currChar = image.access(y, x);
                newImage = newImage.set(x, y, currChar);
            }
        }

        return newImage;
    }

    public boolean isSymmetricH() {
        for (int x = 0;  x < image.getWidth() / 2; x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                final char left = image.access(x, y);
                final char right = image.access(image.getWidth() - x - 1, y);
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

    public AsciiImage clear() {
        AsciiImage newImage = new AsciiImage(image.getWidth(), image.getHeight());
        for (int x = 0; x < newImage.getWidth(); x++) {
            for (int y = 0; y < newImage.getHeight(); y++) {
                newImage = newImage.set(x, y, '.');
            }
        }
        return newImage;
    }

    public AsciiImage line(AsciiPoint from, AsciiPoint to, final char newChar) {
        AsciiImage newImage = image;

        AsciiPoint delta = from.sub(to);
        boolean swapped = false;
        if (Math.abs(delta.getY()) > Math.abs(delta.getX())) {
            to = to.swap();
            from = from.swap();
            delta = delta.swap();
            swapped = true;
        }

        if (from.getX() >= to.getX()) {
            final AsciiPoint tmp = to;
            to = from;
            from = tmp;
        }

        AsciiPoint current = from;
        while (current.getX() <= to.getX()) {
            final int x = (int)(current.getX() + .5);
            final int y = (int)(current.getY() + .5);

            newImage = swapped ? newImage.set(y, x, newChar) : newImage.set(x, y, newChar);
            current = current.add(new AsciiPoint(1, delta.getY() / delta.getX()));
        }

        return newImage;
    }

    public AsciiImage replace(final char oldChar, final char newChar) {
        AsciiImage newImage = image;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (newImage.access(x, y) == oldChar) {
                    newImage = newImage.set(x, y, newChar);
                }
            }
        }
        return newImage;
    }

    public AsciiImage load(final List<String> lines) {
        AsciiImage newImage = image;
        if (lines.size() != image.getHeight()) {
            // return null;
        }

        for (int y = 0;  y < lines.size(); y++) {
            final String line = lines.get(y);
            if (line.length() != image.getWidth()) {
                return null;
            }

            for (int x = 0; x < line.length(); x++) {
                newImage = newImage.set(x, y, line.charAt(x));
            }
        }

        return  newImage;
    }

}
