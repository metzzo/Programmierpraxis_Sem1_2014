package at.pwd.asciishop.app.metrics;

import at.pwd.asciishop.app.AsciiImage;
import at.pwd.asciishop.app.AsciiImageManipulation;

/**
 * Created by Robert on 24.01.2015.
 */
public class UniqueCharsMetric implements Metric<AsciiImage> {
    @Override
    public int distance(AsciiImage o1, AsciiImage o2) {
        final AsciiImageManipulation op1 = new AsciiImageManipulation(o1);
        final AsciiImageManipulation op2 = new AsciiImageManipulation(o2);

        return Math.abs(op1.getUniqueChars() - op2.getUniqueChars());
    }
}