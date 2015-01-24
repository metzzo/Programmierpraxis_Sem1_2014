package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.app.AsciiImage;
import at.pwd.asciishop.app.ShopApp;
import at.pwd.asciishop.app.metrics.MetricSet;
import at.pwd.asciishop.helper.Strings;

/**
 * Created by Robert on 24.01.2015.
 */
public class PrintSavedOperation implements Operation {
    @Override
    public void execute(ShopApp app) throws OperationException {
        final MetricSet<AsciiImage> metric = app.getSavedImages();
        if (metric.size() == 0) {
            app.io().writeLine(Strings.NO_SAVED_IMAGES);
        } else {
            for (final AsciiImage img : metric) {
                app.io().writeLine(img.toString() + '\n');
            }
        }
    }

    @Override
    public boolean shouldSaveOnStack() {
        return false;
    }
}
