package at.pwd.asciishop.app.command;

import at.pwd.asciishop.app.AsciiImageOperation;
import at.pwd.asciishop.app.AsciiPoint;
import at.pwd.asciishop.app.ShopApp;

import java.util.LinkedList;

/**
 * Created by rfischer on 08.12.14.
 */
public class LineCommand implements Command {
    @Override
    public boolean execute(final ShopApp app) {
        return app.expectParams(new ShopApp.ParamRunner() {
            @Override
            public boolean run() {
                if (app.io().readNumeric(app)) return true;
                if (app.io().readNumeric(app)) return true;
                if (app.io().readNumeric(app)) return true;
                if (app.io().readNumeric(app)) return true;
                if (app.io().readString(app)) return true;

                if (app.params().size() != 5) return true;

                final AsciiImageOperation operation = new AsciiImageOperation(app.image());
                app.setImage(operation.line(new AsciiPoint(Integer.valueOf(app.params().get(0)), Integer.valueOf(app.params().get(1))), new AsciiPoint(Integer.valueOf(app.params().get(2)), Integer.valueOf(app.params().get(3))), app.params().get(4).charAt(0)));

                return false;
            }
        });
    }
}
