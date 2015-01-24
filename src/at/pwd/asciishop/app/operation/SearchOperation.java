package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.app.AsciiImage;
import at.pwd.asciishop.app.AsciiImageManipulation;
import at.pwd.asciishop.app.ShopApp;
import at.pwd.asciishop.app.metrics.Metric;
import at.pwd.asciishop.app.metrics.MetricSet;
import at.pwd.asciishop.app.metrics.PixelCountMetric;
import at.pwd.asciishop.app.metrics.UniqueCharsMetric;

/**
 * Created by Robert on 24.01.2015.
 */
public class SearchOperation implements Operation {
    @Override
    public void execute(final ShopApp app) throws OperationException {
        app.expectParams(new ShopApp.ParamRunner() {
            @Override
            public void run() throws OperationException {
                if (app.io().readString(app)) throw new OperationException.InvalidInputException();
                if (app.params().size() != 1) throw new OperationException.InvalidInputException();

                final String type = app.params().get(0);

                if (!type.equals("pixelcount") && !type.equals("uniquechars")) throw new OperationException.InvalidInputException();

                if (app.getSavedImages().size() == 0) {
                    throw new OperationException.InvalidOperationException();
                } else {
                    final MetricSet<AsciiImage> metric = app.getSavedImages().search(app.image(), type.equals("pixelcount") ? new PixelCountMetric() : new UniqueCharsMetric());
                    app.setImage(metric.iterator().next());
                }
            }
        });
    }

    @Override
    public boolean shouldSaveOnStack() {
        return true;
    }
}
