package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.app.AsciiImage;
import at.pwd.asciishop.app.AsciiImageManipulation;
import at.pwd.asciishop.app.ShopApp;

/**
 * Created by rfischer on 08.12.14.
 */
public class CreateOperation implements Operation {
    @Override
    public void execute(final ShopApp app) throws OperationException {
        app.expectParams(new ShopApp.ParamRunner() {
            @Override
            public void run() throws OperationException {
                if (app.io().readNumeric(app)) throw new OperationException.InvalidInputException();
                if (app.io().readNumeric(app)) throw new OperationException.InvalidInputException();
                if (app.io().readString(app)) throw new OperationException.InvalidInputException();

                if (app.params().size() != 3)  throw new OperationException.InvalidInputException();

                final int width = Integer.valueOf(app.params().get(0));
                final int height = Integer.valueOf(app.params().get(1));
                final String charset = app.params().get(2);

                if (width <= 0 || height <= 0) throw new OperationException.InvalidInputException();

                final AsciiImageManipulation operation = new AsciiImageManipulation(new AsciiImage(width, height, charset));
                app.setImage(operation.clear());
            }
        });
    }

    public boolean shouldSaveOnStack() {
        return false;
    }
}
