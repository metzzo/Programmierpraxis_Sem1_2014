package at.pwd.asciishop.app;

/**
 * Class enables simple drawing functions like drawLabel, drawBar, ...
 * Created by Robert on 06.11.2014.
 */
public class StringRenderer {
    /**
     * character 'c' is repeated 'count' times
     */
    public String repeat(final char c, final int count) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(c);
        }
        return builder.toString();
    }

    /**
     * ensures that a given label has the given size (in count)
     * if its too small it is filled with ' ' left padding
     * if its too big it is truncated
     */
    public String drawLabel(final String label, final int count) {
        if (label.length() >= count) {
            return label.substring(0, count);
        } else {
            final StringBuilder builder = new StringBuilder(label);
            builder.append(repeat(' ', count - label.length()));
            return builder.toString();
        }
    }

    /**
     * draws a bar with 'value' segments and text 'label' in the format
     * WS2009  |###############               |
     */
    public String drawBar(final String label, final int value) {
        final StringBuilder builder = new StringBuilder();

        return builder.append(drawLabel(label, 8))
                      .append('|')
                      .append(repeat('#', value))
                      .append(repeat(' ', 30 - value))
                      .append('|')
                      .toString();

    }

    public String drawBar(final String label, final double value) {
        return drawBar(label, (int)Math.round(value * 30));
    }
}
