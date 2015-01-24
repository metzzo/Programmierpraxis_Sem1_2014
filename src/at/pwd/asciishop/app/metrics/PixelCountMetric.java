package at.pwd.asciishop.app.metrics;

import at.pwd.asciishop.app.AsciiImage;

/**
 * Created by Robert on 24.01.2015.
 */
public class PixelCountMetric implements Metric<AsciiImage> {
    @Override
    public int distance(AsciiImage o1, AsciiImage o2) {
        return Math.abs(o1.getWidth() * o1.getHeight() - o2.getWidth() * o2.getHeight());
    }
}
