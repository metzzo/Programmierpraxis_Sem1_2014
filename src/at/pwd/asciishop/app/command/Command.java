package at.pwd.asciishop.app.command;

import at.pwd.asciishop.app.ShopApp;

/**
 * Created by rfischer on 08.12.14.
 */
public interface Command {
    public boolean execute(final ShopApp app);
}
