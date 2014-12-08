package at.pwd.asciishop.app.command;

import at.pwd.asciishop.app.AsciiImageOperation;
import at.pwd.asciishop.app.ShopApp;

/**
 * Created by rfischer on 08.12.14.
 */
public class ClearCommand implements Command {
    @Override
    public boolean execute(final ShopApp app) {
        final AsciiImageOperation operation = new AsciiImageOperation(app.image());
        app.setImage(operation.clear());

        return false;
    }
}
