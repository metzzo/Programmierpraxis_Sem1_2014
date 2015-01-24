package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.app.ShopApp;

/**
 * Created by rfischer on 08.12.14.
 */
public interface Operation {
    public void execute(final ShopApp app) throws OperationException;
    public boolean shouldSaveOnStack();
}
