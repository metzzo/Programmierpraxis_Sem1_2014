package at.pwd.asciishop.app.command;

import at.pwd.asciishop.app.ShopApp;

/**
 * Created by rfischer on 08.12.14.
 */
public class PrintCommand implements Command {
    @Override
    public boolean execute(final ShopApp app) {
        app.io().writeLine(app.image().toString() + '\n');
        return false;
    }
}
