package at.pwd.asciishop.app.command;

import at.pwd.asciishop.app.AsciiImageOperation;
import at.pwd.asciishop.app.ShopApp;

/**
 * Created by Robert on 08.12.2014.
 */
public class StraightenCommand implements Command {
    @Override
    public boolean execute(final ShopApp app) {
        return app.expectParams(new ShopApp.ParamRunner() {
            @Override
            public boolean run() {
                if (app.io().readString(app)) return true;

                final AsciiImageOperation operation = new AsciiImageOperation(app.image());
                app.setImage(operation.straighten(app.params().get(0).charAt(0)));

                return false;
            }
        });
    }
}
