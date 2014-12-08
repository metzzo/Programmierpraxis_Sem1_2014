package at.pwd.asciishop.app;

import java.util.LinkedList;

/**
 * It would be better to just say: public class AsciiStack extends LinkedList<AsciiImage> { }
 * Created by rfischer on 02.12.14.
 */
public class AsciiStack {
    private AsciiImage[] images;
    private int pointer;
    private int increment;

    public AsciiStack(final int increment) {
        this.increment = increment;
        this.images = new AsciiImage[0];
        this.pointer = 0;
    }

    public void push(final AsciiImage image) {
        if (images.length <= pointer) {
            resize(images.length + increment);
        }
        images[pointer++] = image;
    }

    public AsciiImage pop() {
        final AsciiImage img = images[--pointer];
        if (images.length - pointer > increment) {
            resize(images.length - increment);
        }
        return img;
    }

    public AsciiImage peek() {
        return images[pointer];
    }

    public boolean empty() {
        return pointer == 0;
    }

    public int size() {
        return  pointer;
    }

    public int capacity() {
        return images.length;
    }

    private void resize(final int newSize) {
        final AsciiImage[] newImages = new AsciiImage[newSize];
        for (int i = 0; i < images.length && i < newImages.length; i++) {
            newImages[i] = images[i];
        }
        images = newImages;
    }
}
