package at.pwd.asciishop.app.command;

import at.pwd.asciishop.app.AsciiImageOperation;
import at.pwd.asciishop.app.ShopApp;

/**
 * Created by rfischer on 08.12.14.
 */
public class SymmetricCommand implements Command {
    @Override
    public boolean execute(final ShopApp app) {
        final AsciiImageOperation operation = new AsciiImageOperation(app.image());
        app.io().writeLine("" + operation.isSymmetricH());

        return false;
    }
}
