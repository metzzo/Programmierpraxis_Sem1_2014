package at.pwd.asciishop.app;

import java.util.Set;
import java.util.HashSet;
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

    public AsciiImage transpose() {
        AsciiImage newImage = new AsciiImage(image.getHeight(), image.getWidth());
        for (int x = 0; x < newImage.getHeight(); x++) {
            for (int y = 0;  y < newImage.getWidth(); y++) {
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

    public AsciiImage line(Vec from, Vec to, final char newChar) {
        AsciiImage newImage = image;

        Vec delta = from.sub(to);
        boolean swapped = false;
        if (Math.abs(delta.y()) > Math.abs(delta.y())) {
            to = to.swap();
            from = from.swap();
            delta = delta.swap();
            swapped = true;
        }

        if (from.x() >= to.x()) {
            final Vec tmp = to;
            to = from;
            from = tmp;
        }

        Vec current = from;
        while (current.x() <= to.x()) {
            final int x = (int)(current.x() + .5);
            final int y = (int)(current.y() + .5);

            newImage = swapped ? newImage.set(y, x, newChar) : newImage.set(x, y, newChar);
            current = current.add(new Vec(1, delta.y() / delta.x()));
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

        for (int y = 0;  y < lines.size(); y++) {
            final String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                newImage = newImage.set(x, y, line.charAt(x));
            }
        }

        return  newImage;
    }

    public static class Vec {
        private double x, y;

        public Vec(final double x, final double y) {
            this.x = x;
            this.y = y;
        }

        public double x() {
            return this.x;
        }

        public double y() {
            return this.y;
        }

        public Vec sub(final Vec v) {
            return new Vec(x() - v.x(), y() - v.y());
        }
        public Vec add(final Vec v) {
            return new Vec(x() + v.x(), y() + v.y());
        }
        public Vec swap() {
            return new Vec(y(), x());
        }
    }
}
