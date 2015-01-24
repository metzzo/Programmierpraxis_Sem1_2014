package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.app.AsciiImage;
import at.pwd.asciishop.app.AsciiImageManipulation;
import at.pwd.asciishop.app.ShopApp;
import at.pwd.asciishop.helper.Strings;

/**
 * Created by rfischer on 08.12.14.
 */
public class FillOperation implements Operation {
    @Override
    public void execute(final ShopApp app) throws OperationException {
        app.expectParams(new ShopApp.ParamRunner() {
            @Override
            public void run() throws OperationException {
                if (app.io().readNumeric(app)) throw new OperationException.InvalidInputException();
                if (app.io().readNumeric(app)) throw new OperationException.InvalidInputException();
                if (app.io().readString(app)) throw new OperationException.InvalidInputException();

                if (app.params().size() != 3) throw new OperationException.InvalidInputException();

                final int x  = Integer.valueOf(app.params().get(0));
                final int y  = Integer.valueOf(app.params().get(1));
                final char c = app.params().get(2).charAt(0);

                final AsciiImageManipulation renderer = new AsciiImageManipulation(app.image());
                final AsciiImage newImage          = renderer.fill(x, y, c);
                if (newImage == app.image()) {
                    app.io().writeLine(Strings.INVALID_OPERATION);
                    app.setImage(null);
                } else {
                    app.setImage(newImage);
                }
            }
        });
    }

    public boolean shouldSaveOnStack() {
        return true;
    }
}
