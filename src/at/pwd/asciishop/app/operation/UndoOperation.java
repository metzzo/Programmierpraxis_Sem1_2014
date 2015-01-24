package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.app.ShopApp;
import at.pwd.asciishop.helper.Strings;

/**
 * Created by rfischer on 08.12.14.
 */
public class UndoOperation implements Operation {
    @Override
    public void execute(final ShopApp app) throws OperationException {
        if (!app.imageStack().empty()) {
            app.imageStack().pop();
        } else {
            app.io().writeLine(Strings.STACK_EMPTY);
        }

        if (!app.imageStack().empty()) {
            app.setImage(app.imageStack().peek());
        } else {
            app.setImage(null);
        }
    }

    public boolean shouldSaveOnStack() {
        return false;
    }
}