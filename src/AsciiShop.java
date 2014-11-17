import at.pwd.asciishop.app.ShopApp;
import at.pwd.asciishop.app.AsciiImage;
import at.pwd.asciishop.app.AsciiImageOperation;

/**
 * Entry point for AsciiShop
 * Info: This code only uses comments if REALLY necessary - no bloated javadoc/etc. because good code is self documenting,
 * which this code aims to be
 * For better readable code see my GitHub Repository: https://github.com/metzzo/Programmierpraxis_Sem1_2014/tree/uebung3
 *
 * Created by Robert on 06.11.2014.
 */
public class AsciiShop {
    public static void main(final String[] args) {
        new ShopApp().run();
    }

    public static void fill(final String[] image, final int fillx, final int filly, final char newChar) {
        final AsciiImageOperation renderer = new AsciiImageOperation(new AsciiImage(image));
        final AsciiImage finalImage = renderer.fill(fillx, filly, newChar);
        for (int y = 0; y < image.length; y++) {
            final StringBuilder builder = new StringBuilder();
            for (int x = 0; x < image[0].length(); x++) {
                builder.append(finalImage.access(x, y));
            }
            image[y] = builder.toString();
        }
    }
}
