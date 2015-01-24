package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.app.AsciiImage;
import at.pwd.asciishop.app.ShopApp;

/**
 * Created by Robert on 24.01.2015.
 */
public class SaveOperation implements Operation {
    @Override
    public void execute(ShopApp app) throws OperationException {
        app.getSavedImages().add(new AsciiImage(app.image()));
    }

    @Override
    public boolean shouldSaveOnStack() {
        return false;
    }
}
