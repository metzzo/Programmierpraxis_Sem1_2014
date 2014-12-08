package at.pwd.asciishop.app.command;

import at.pwd.asciishop.app.ShopApp;

/**
 * Created by rfischer on 08.12.14.
 */
public class UndoCommand implements Command {
    @Override
    public boolean execute(final ShopApp app) {

        if (!app.imageStack().empty()) {
            app.imageStack().pop();
            app.setImage(app.imageStack().peek());

            app.io().writeLine("STACK USAGE " + app.imageStack().size() + "/" + app.imageStack().capacity());
        }

        if (app.imageStack().empty()) {
            app.io().writeLine("STACK EMPTY");
        }

        return false;
    }
}