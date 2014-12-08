package at.pwd.asciishop.app.command;

import at.pwd.asciishop.app.AsciiImage;
import at.pwd.asciishop.app.AsciiImageOperation;
import at.pwd.asciishop.app.ShopApp;

/**
 * Created by rfischer on 08.12.14.
 */
public class CreateCommand implements Command {
    @Override
    public boolean execute(final ShopApp app) {
        return app.expectParams(new ShopApp.ParamRunner() {
            @Override
            public boolean run() {
                if (app.io().readNumeric(app)) return true;
                if (app.io().readNumeric(app)) return true;

                if (app.params().size() != 2) return true;

                final int width = Integer.valueOf(app.params().get(0));
                final int height = Integer.valueOf(app.params().get(1));

                if (width <= 0 || height <= 0) return true;

                app.setImage(new AsciiImage(width, height));
                final AsciiImageOperation operation = new AsciiImageOperation(app.image());
                app.setImage(operation.clear());

                return false;
            }
        });
    }
}
