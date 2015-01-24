package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.app.AsciiImageManipulation;
import at.pwd.asciishop.app.ShopApp;

/**
 * Created by rfischer on 08.12.14.
 */
public class ClearOperation implements Operation {
    @Override
    public void execute(final ShopApp app) throws OperationException {
        final AsciiImageManipulation operation = new AsciiImageManipulation(app.image());
        app.setImage(operation.clear());
    }

    public boolean shouldSaveOnStack() {
        return true;
    }
}