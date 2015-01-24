package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.app.ShopApp;

/**
 * Created by Robert on 23.01.2015.
 */
public class InvalidOperation implements Operation {
    @Override
    public void execute(ShopApp app) throws OperationException {
        throw new OperationException.InvalidCommandException();
    }

    @Override
    public boolean shouldSaveOnStack() {
        return false;
    }
}
