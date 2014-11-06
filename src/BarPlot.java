import at.pwd.asciishop.app.ShopApp;
import at.pwd.asciishop.app.StringRenderer;

/**
 * Entry point for BarPlot
 * Info: This code only uses comments if REALLY necessary - no bloated javadoc/etc. because good code is self documenting,
 * which this code aims to be
 * For better readable code see my GitHub Repository: https://github.com/metzzo/Programmierpraxis_Sem1_2014/tree/uebung2
 *
 * Created by Robert on 06.11.2014.
 */
public class BarPlot {
    public static void main(final String[] args) {
        new ShopApp().run();
    }

    public static String repeat(char c, int n) {
        return new StringRenderer().repeat(c, n);
    }

    public static String drawLabel(String label, int n) {
        return new StringRenderer().drawLabel(label, n);
    }

    public static String drawBar(String label, int value) {
        return new StringRenderer().drawBar(label, value);
    }

    public static String drawBar(String label, double value) {
        return new StringRenderer().drawBar(label, value);
    }
}
