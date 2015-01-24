package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.app.AsciiImageManipulation;
import at.pwd.asciishop.app.AsciiPoint;
import at.pwd.asciishop.app.ShopApp;

/**
 * Created by rfischer on 08.12.14.
 */
public class LineOperation implements Operation {
    @Override
    public void execute(final ShopApp app) throws OperationException {
        app.expectParams(new ShopApp.ParamRunner() {
            @Override
            public void run() throws OperationException {
                if (app.io().readNumeric(app)) throw new OperationException.InvalidInputException();
                if (app.io().readNumeric(app)) throw new OperationException.InvalidInputException();
                if (app.io().readNumeric(app)) throw new OperationException.InvalidInputException();
                if (app.io().readNumeric(app)) throw new OperationException.InvalidInputException();
                if (app.io().readString(app)) throw new OperationException.InvalidInputException();

                if (app.params().size() != 5) throw new OperationException.InvalidInputException();

                final AsciiImageManipulation operation = new AsciiImageManipulation(app.image());
                app.setImage(operation.line(new AsciiPoint(Integer.valueOf(app.params().get(0)), Integer.valueOf(app.params().get(1))), new AsciiPoint(Integer.valueOf(app.params().get(2)), Integer.valueOf(app.params().get(3))), app.params().get(4).charAt(0)));
            }
        });
    }

    public boolean shouldSaveOnStack() {
        return true;
    }
}
