package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.app.AsciiImageManipulation;
import at.pwd.asciishop.app.ShopApp;

/**
 * Created by rfischer on 08.12.14.
 */
public class LoadOperation implements Operation {
    private String endMarker;

    @Override
    public void execute(final ShopApp app) throws OperationException {
        app.expectParams(new ShopApp.ParamRunner() {
            @Override
            public void run() throws OperationException {
                if (app.io().readString(app)) throw new OperationException.InvalidInputException();
                if (app.params().size() != 1) throw new OperationException.InvalidInputException();
                endMarker = app.params().get(0);
            }
        });
        app.expectParams(new ShopApp.ParamRunner() {
            @Override
            public void run() throws OperationException {
                if (app.io().readHereDoc(endMarker, app))  throw new OperationException.InvalidInputException();

                final AsciiImageManipulation operation = new AsciiImageManipulation(app.image());
                app.setImage(operation.load(app.params()));

                if (app.image() == null)  throw new OperationException.InvalidInputException();
            }
        });
    }

    public boolean shouldSaveOnStack() {
        return true;
    }
}
