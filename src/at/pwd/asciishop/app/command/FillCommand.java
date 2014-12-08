package at.pwd.asciishop.app.command;

import at.pwd.asciishop.app.AsciiImage;
import at.pwd.asciishop.app.AsciiImageOperation;
import at.pwd.asciishop.app.ShopApp;
import at.pwd.asciishop.helper.Strings;

import java.util.LinkedList;

/**
 * Created by rfischer on 08.12.14.
 */
public class FillCommand implements Command {
    @Override
    public boolean execute(final ShopApp app) {
        return app.expectParams(new ShopApp.ParamRunner() {
            @Override
            public boolean run() {
                if (app.io().readNumeric(app)) return true;
                if (app.io().readNumeric(app)) return true;
                if (app.io().readString(app)) return true;

                if (app.params().size() != 3) return true;

                final int x  = Integer.valueOf(app.params().get(0));
                final int y  = Integer.valueOf(app.params().get(1));
                final char c = app.params().get(2).charAt(0);

                final AsciiImageOperation renderer = new AsciiImageOperation(app.image());
                final AsciiImage newImage          = renderer.fill(x, y, c);
                if (newImage == app.image()) {
                    app.io().writeLine(Strings.INVALID_OPERATION);
                    app.setImage(null);
                } else {
                    app.setImage(newImage);
                }

                return false;
            }
        });
    }
}
