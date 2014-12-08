package at.pwd.asciishop.app;

/**
* Created by rfischer on 02.12.14.
*/
public class AsciiPoint {
    private double x, y;

    public AsciiPoint(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    public AsciiPoint(final int x, final int y) {
        this((double)x, (double)y);
    }
    public AsciiPoint() {
        this(0, 0);
    }

    public String toString() {
        return "(" + (int)(x + .5) + "," + (int)(y + .5) + ")";
    }

    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }

    public AsciiPoint sub(final AsciiPoint v) {
        return new AsciiPoint(getX() - v.getX(), getY() - v.getY());
    }
    public AsciiPoint add(final AsciiPoint v) {
        return new AsciiPoint(getX() + v.getX(), getY() + v.getY());
    }
    public AsciiPoint div(final double factor) { return new AsciiPoint(getX() / factor, getY() / factor); }
    public AsciiPoint mul(final double factor) { return new AsciiPoint(getX() * factor, getY() * factor); }

    public AsciiPoint swap() {
        return new AsciiPoint(getY(), getX());
    }
}
