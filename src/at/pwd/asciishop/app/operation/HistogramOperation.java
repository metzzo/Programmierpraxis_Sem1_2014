package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.app.AsciiImage;
import at.pwd.asciishop.app.AsciiImageManipulation;
import at.pwd.asciishop.app.ShopApp;

/**
 * Created by Robert on 24.01.2015.
 */
public class HistogramOperation implements Operation {
    @Override
    public void execute(ShopApp app) throws OperationException {
        final AsciiImage image = new AsciiImageManipulation(app.image()).histogram();
        app.io().writeLine(image.toString());
    }

    @Override
    public boolean shouldSaveOnStack() {
        return false;
    }
}
