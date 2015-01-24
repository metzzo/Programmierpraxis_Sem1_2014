package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.app.ShopApp;

/**
 * Created by rfischer on 08.12.14.
 */
public class PrintOperation implements Operation {
    @Override
    public void execute(final ShopApp app) throws OperationException {
        app.io().writeLine(app.image().toString() + '\n');
    }

    public boolean shouldSaveOnStack() {
        return false;
    }
}
