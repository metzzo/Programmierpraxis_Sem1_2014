package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.app.AsciiImageManipulation;
import at.pwd.asciishop.app.ShopApp;

/**
 * Created by Robert on 24.01.2015.
 */
public class BinaryOperation implements Operation {
    @Override
    public void execute(final ShopApp app) throws OperationException {
        app.expectParams(new ShopApp.ParamRunner() {
            @Override
            public void run() throws OperationException {
                if (app.io().readString(app)) throw new OperationException.InvalidInputException();
                if (app.params().size() != 1)  throw new OperationException.InvalidInputException();

                final AsciiImageManipulation operation = new AsciiImageManipulation(app.image());
                app.setImage(operation.threshold(app.params().get(0).charAt(0)));
            }
        });
    }

    @Override
    public boolean shouldSaveOnStack() {
        return true;
    }
}
