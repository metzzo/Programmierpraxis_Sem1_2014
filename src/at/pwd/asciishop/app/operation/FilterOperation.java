package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.app.AsciiImageManipulation;
import at.pwd.asciishop.app.ShopApp;

/**
 * Created by Robert on 24.01.2015.
 */
public class FilterOperation implements Operation {
    @Override
    public void execute(final ShopApp app) throws OperationException {
        app.expectParams(new ShopApp.ParamRunner() {
            @Override
            public void run() throws OperationException {
                if (app.io().readString(app)) throw new OperationException.InvalidInputException();
                if (app.params().size() != 1) throw new OperationException.InvalidInputException();

                final String type = app.params().get(0);

                if (!type.equals("median")) throw new OperationException.InvalidInputException();

                final AsciiImageManipulation operation = new AsciiImageManipulation(app.image());
                app.setImage(operation.filter(AsciiImageManipulation.FilterType.MEDIAN));

            }
        });
    }

    public boolean shouldSaveOnStack() {
        return true;
    }
}
