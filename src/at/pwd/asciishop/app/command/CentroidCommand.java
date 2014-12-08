package at.pwd.asciishop.app.command;

import at.pwd.asciishop.app.AsciiImageOperation;
import at.pwd.asciishop.app.ShopApp;

import java.util.LinkedList;

/**
 * Created by rfischer on 08.12.14.
 */
public class CentroidCommand implements Command {
    @Override
    public boolean execute(final ShopApp app) {
        return app.expectParams(new ShopApp.ParamRunner() {
            @Override
            public boolean run() {
                if (app.io().readString(app)) return true;

                if (app.params().size() != 1) return true;

                final AsciiImageOperation operation = new AsciiImageOperation(app.image());
                app.io().writeLine(operation.calculateCentroid(app.params().get(0).charAt(0)).toString());


                return false;
            }
        });
    }
}
